package com.qycloud.oatos.license.service;

import com.qycloud.oatos.license.domain.User;

/**
 * Created by jiuyuehe on 2015/2/4.
 */
public interface UserService {

    String  login(String email,String password);

    User getUserInfo(long id);

}
