package ua.rd.service;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import ua.rd.domain.User;

/**
 * Created by irina on 23.09.17.
 */

@Service
public class UserService {

    @Lookup
    public User createUser(String excpectedNickname) {
        return null;
    }
}
