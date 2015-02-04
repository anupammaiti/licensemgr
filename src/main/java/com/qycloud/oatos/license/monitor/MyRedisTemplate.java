package com.qycloud.oatos.license.monitor;

import com.qycloud.oatos.license.domain.User;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by jiuyuehe on 2015/2/4.
 */

@Component
public class MyRedisTemplate<String, User> extends RedisTemplate<String, User> {
    public MyRedisTemplate() {
    }

    /**
     * Constructs a new <code>StringRedisTemplate</code> instance ready to be used.
     *
     * @param connectionFactory connection factory for creating new connections
     */
    public MyRedisTemplate(RedisConnectionFactory connectionFactory) {
        this();
        setConnectionFactory(connectionFactory);
        afterPropertiesSet();
    }

    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }
    /**
     * http://stackoverflow.com/questions/27521672/how-autowired-redistemplatestring-long
     */
}