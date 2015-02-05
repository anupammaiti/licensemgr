package com.qycloud.oatos.test.service.web;

import com.qycloud.oatos.license.ApplicationApp;
import com.qycloud.oatos.license.domain.User;
import com.qycloud.oatos.license.utils.LicSecurity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by jiuyuehe on 2015/2/4.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationApp.class)
public class RedisTest {

    @Autowired
    private StringRedisTemplate template;


    @Test
    public void test1(){
        this.template.opsForValue().set("one","7777");
        this.template.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                ((StringRedisConnection)connection).set("two", "888");
                return null;
            }
        });
    }


    @Test
    public void testSetValue() {
//        String key = "spring.boot.redis.test";
        User u = new User();
        u.setRole("1");
        u.setPassword("45645");
        u.setEmail("1111");
        u.setName("oooo");
        u.setId(233l);
        String key1 = "spring.boot.redis.user1";
        ValueOperations<String, String> ops = this.template.opsForValue();
        ops.set(key1,u.toString());
        ops.set(LicSecurity.encryptBASE64(key1.getBytes()),u.toString());

        System.out.println("expire is :" + this.template.expire(key1, 1000, TimeUnit.SECONDS));

        System.out.println("expire is :" + this.template.getExpire(key1));

        System.out.println("Found key " + key1 + ", value=" + ops.get(key1));


    }

    @Test
    public void testSecurity() throws Exception{
        String data = LicSecurity.encryptBASE64("http://aub.iteye.com/".getBytes());
        System.out.println("加密前："+data);

        byte[] byteArray = LicSecurity.decryptBASE64(data);
        System.out.println("解密后："+new String(byteArray));


        String data2 = LicSecurity.encryptBASE64("中文--……&*&".getBytes());
        System.out.println("加密前："+data2);

        byte[] byteArray2 = LicSecurity.decryptBASE64(data2);
        System.out.println("解密后："+new String(byteArray2));
    }


}
