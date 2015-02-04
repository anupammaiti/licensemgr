package com.qycloud.oatos.license.service;

import com.qycloud.oatos.license.dao.UserRepository;
import com.qycloud.oatos.license.domain.User;
import com.qycloud.oatos.license.monitor.MyRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * Created by jiuyuehe on 2015/2/4.
 */

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private RedisTemplate<String,String> template;

    @Autowired
    private MyRedisTemplate<String,User> myTemplate;


    @Override
    public String login(String email, String password) {
        User user = this.userRepository.findByEmailAndPassword(email, password);

        if (user == null)  return null;

        String key1 = "lic" + "$" + user.getId() + "$" + user.getRole() + "$" + user.getName() + "$" + user.getEmail();

        ValueOperations<String, User> ops = this.myTemplate.opsForValue();

        if (!this.myTemplate.hasKey(key1)) {
            ops.set(key1, user);
        }
        return key1;
    }

    @Override
    public User getUserInfo(long id) {
        return null;
    }
}
