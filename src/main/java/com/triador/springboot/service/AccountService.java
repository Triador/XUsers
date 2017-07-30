package com.triador.springboot.service;

import com.triador.springboot.model.User;

/**
 * Created by antonandreev on 08/04/2017.
 */
public interface AccountService {
    void addSession(String sessionId, User dataSet);
    void addNewUser(User usersDataSet);
    void deleteSession(String sessionId);
    User getUserBySessionId(String sessionId);
    User getUserByName(String name);
    boolean isSessionExist(String sessionId);
}
