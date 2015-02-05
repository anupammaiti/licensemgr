package com.qycloud.oatos.license.service;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.qycloud.oatos.license.dao.UserRepository;
import com.qycloud.oatos.license.domain.User;
import com.qycloud.oatos.license.utils.Json;
import com.qycloud.oatos.license.utils.LicSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by jiuyuehe on 2015/2/4.
 */

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate<String, String> template;

//    @Autowired
//    private RedisTemplate<String,User> myTemplate;


    @Override
    public String login(String email, String password) throws Exception {
        User user = this.userRepository.findByEmailAndPassword(email, password);
        System.out.println(user);
        if (user == null) return null;
        String value = Json.toJson(user);
        String key = "lic" + "$" + user.getId() + "$" + user.getRole();
        String key1 = LicSecurity.encryptBASE64(key.getBytes());
        ValueOperations<String, String> ops = this.template.opsForValue();
        ops.set(key, value);
        template.expire(key, 10, TimeUnit.MINUTES);
        return key1;
    }

    @Override
    public User getUserInfo(long id) {
        return null;
    }
}
