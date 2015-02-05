package com.qycloud.oatos.license;

import com.qycloud.oatos.license.domain.User;
import com.qycloud.oatos.license.monitor.AuthorAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by jiuyuehe on 2015/1/16.
 */


@SpringBootApplication
public class ApplicationApp extends WebMvcConfigurerAdapter {


    @Autowired
    private RedisTemplate<String,String> template;


    @Autowired
    private AuthorAccess authorAccess;


//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//        return new JedisConnectionFactory();
//    }
//
//    @Bean
//    RedisTemplate<String, User> redisTemplate() {
//        final RedisTemplate<String, User> template = new RedisTemplate<String, User>();
//        template.setConnectionFactory(jedisConnectionFactory());
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setHashValueSerializer(new GenericToStringSerializer<User>(User.class));
//        template.setValueSerializer(new GenericToStringSerializer<User>(User.class));
//        return template;
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // super.addInterceptors(registry);
        registry.addInterceptor(authorAccess).addPathPatterns("/api/sc/**");
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationApp.class, args);
    }
}
