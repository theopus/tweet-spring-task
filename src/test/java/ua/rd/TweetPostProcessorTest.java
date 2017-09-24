package ua.rd;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.rd.config.TweetsConfig;
import ua.rd.repository.InMemTweetReposoitory;
import ua.rd.service.*;

/**
 * Created by irina on 24.09.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TweetsConfig.class, TweetServiceImpl.class, TweetFactory.class, TweetSizePostprocessor.class, InMemTweetReposoitory.class})
public class TweetPostProcessorTest {


    @Autowired
    private TweetService tweetService;


    @Test(expected = TweetSizeException.class)
    public void bigBadTweet() throws Exception {
        System.out.println(tweetService);
        tweetService.tweet(null, "sddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsdd");

    }
}
