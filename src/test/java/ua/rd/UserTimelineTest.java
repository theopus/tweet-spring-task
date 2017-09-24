package ua.rd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.rd.config.TweetsConfig;
import ua.rd.domain.RepliedTweet;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.repository.InMemTweetReposoitory;
import ua.rd.service.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by irina on 24.09.17.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UserTimeLineServiceImpl.class,TweetServiceImpl.class, TweetsConfig.class, TweetFactory.class, InMemTweetReposoitory.class})
public class UserTimelineTest {

    @Autowired
    private TweetService tweetService;
    @Autowired
    private UserTimeLineService timeLineService;

    @Test
    public void getUserTimeLineWithSelfOnlyZero() throws Exception {
        User user1 = new User("1");
        User user2 = new User("2");

        Tweet tweetOne = tweetService.tweet(user1, "tweetOne");
        Tweet tweetTwo = tweetService.tweet(user1, "tweetTwo");
        RepliedTweet reply = tweetService.reply(user2, "reply on this sh**", tweetOne);

        List<Tweet> expected = new ArrayList<Tweet>(){
            {
                add(tweetOne);
                add(tweetTwo);
                add(reply);
            }
        };

        Iterable<Tweet> actual = timeLineService.getUserTimeline(user1);

        assertEquals(expected,actual);
    }
}
