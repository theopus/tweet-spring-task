package ua.rd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ua.rd.domain.User;

/**
 * Created by irina on 23.09.17.
 */

@Configuration
public class UsersConfig {

    private int usersCount = 0;

    @Bean
    @Scope("prototype")
    public User createUser(String nickname){
        User user = new User(nickname);
        user.setId(usersCount++);
        return user;
    }
}
