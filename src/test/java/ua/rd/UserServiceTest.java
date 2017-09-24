package ua.rd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.rd.config.UsersConfig;
import ua.rd.domain.User;
import ua.rd.service.UserService;

import static org.junit.Assert.assertEquals;

/**
 * Created by irina on 23.09.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UsersConfig.class, UserService.class})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void createUserTest() throws Exception {
        String excpectedNickname = "user1";
        User user = userService.createUser(excpectedNickname);
        String actual = user.getNickname();

        assertEquals(excpectedNickname, actual);
    }
}
