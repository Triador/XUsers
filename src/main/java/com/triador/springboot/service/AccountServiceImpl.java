package com.triador.springboot.service;



import com.triador.springboot.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by antonandreev on 08/04/2017.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    private final Map<String, User> loginToProfile;
    private final Map<String, User> sessionIdToProfile;

    private AccountServiceImpl() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }

    public void addNewUser(User user) {
        loginToProfile.put(user.getUsername(), user);
    }

    public User getUserByName(String name) {
        return loginToProfile.get(name);
    }

    public User getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public boolean isSessionExist(String sessionId) {
        return getUserBySessionId(sessionId) != null;
    }

    public void addSession(String sessionId, User user) {
        sessionIdToProfile.put(sessionId, user);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }

}
