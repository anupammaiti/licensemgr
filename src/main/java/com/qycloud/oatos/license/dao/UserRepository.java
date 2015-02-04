package com.qycloud.oatos.license.dao;

import com.qycloud.oatos.license.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jiuyuehe on 2015/1/19.
 */
public interface UserRepository extends JpaRepository<User,Long>{

    User findByEmail(String email);

    User findByEmailAndPassword(String email,String password);

}
