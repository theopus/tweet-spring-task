package ua.rd;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.rd.config.TweetsConfig;
import ua.rd.domain.*;
import ua.rd.repository.TweetRepository;
import ua.rd.service.TweetFactory;
import ua.rd.service.TweetService;
import ua.rd.service.TweetServiceImpl;

import static org.junit.Assert.assertEquals;

/**
 * Created by irina on 23.09.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TweetsConfig.class, TweetFactory.class})
public class TweetServiceImplTest {


    @Autowired
    private TweetFactory factory;

    private TweetService service;

    @Before
    public void setUp() throws Exception {
        service = new TweetServiceImpl(factory, new TweetRepository(){

            @Override
            public Iterable<Tweet> getAll() {
                return null;
            }

            @Override
            public Iterable<Tweet> getAllOwnedBy(User user) {
                return null;
            }

            @Override
            public Tweet saveTweet(Tweet tweet) {
                return null;
            }

            @Override
            public int getCount() {
                return 0;
            }
        });
    }

    @Test
    public void tweetTweetTest() throws Exception {
        String expected = "dsad";

        Tweet tweet = service.tweet(null, expected);
        String actual = tweet.getText();
        assertEquals(expected,actual);
    }

    @Test
    public void tweetTweetWithUser() throws Exception {
        User expected = new User("batya");

        Tweet tweet = service.tweet(expected, null);
        User actual = tweet.getUser();

        assertEquals(expected,actual);
    }

    @Test
    public void tweetWithoutLikes() throws Exception {
        int expected = 0;
        Tweet txt = service.tweet(null, "txt");
        int actual = txt.getLikeCount();

        assertEquals(expected,actual);
    }


    @Test
    public void likeTweet() throws Exception {
        int expected = 1;
        Tweet tweet = service.tweet(null, "txt");
        int actual  = service.like(null, tweet).getLikeCount();

        assertEquals(expected,actual);
    }

    @Test
    public void getReplyedTweets() throws Exception {
        Tweet expected = new SimpleTweet("txt", null);
        RepliedTweet repliedTweet = service.reply(null, "txt", expected);

        Tweet actual = repliedTweet.getRepliedTo();

        assertEquals(expected, actual);

    }

    @Test
    public void getReplyCountAfterReply(){
        int expected = 1;
        Tweet tweet = new SimpleTweet("txt", null);
        service.reply(null, "txt", tweet);

        int actual = tweet.getReplyCount();
        assertEquals(expected, actual);
    }

    @Test
    public void reTweetCounter() throws Exception {
        int expected = 1;
        Tweet tweet = new SimpleTweet("inital Tweet", null);

        service.reTweet(null, "retweet",tweet);
        int actual = tweet.getReTweetCount();

        assertEquals(expected,actual);

    }

    @Test
    public void reTweetOfReTweetCounter() throws Exception {
        int expected = 2;
        Tweet initial = new SimpleTweet("inital Tweet", null);

        ReTweet reTweet = service.reTweet(null, "first retweet", initial);
        ReTweet reTweet2 = service.reTweet(null, "second retweet", reTweet);

        int actual = initial.getReTweetCount();

        assertEquals(expected,actual);
    }

    @Test
    public void getReTweetedToTwoTimes() throws Exception {
        Tweet expected = new SimpleTweet("inital Tweet", null);

        ReTweet reTweet = service.reTweet(null, "first retweet", expected);
        ReTweet reTweet2 = service.reTweet(null, "second retweet", reTweet);

        Tweet actual = reTweet2.getReTweeted();
        assertEquals(expected,actual);
    }

    @Test
    public void getReTweetedToMultiTimes() throws Exception {
        Tweet expected = new SimpleTweet("inital Tweet", null);

        ReTweet reTweet = service.reTweet(null, "first retweet", expected);
        for (int i = 2; i < 6; i++) {
            reTweet  = service.reTweet(null, i + " retweet", reTweet);
        }


        Tweet actual = reTweet.getReTweeted();
        assertEquals(expected,actual);
    }

    @Test
    public void getReTweetedToMultiTimesLikeLastOne() throws Exception {
        int expected = 1;
        Tweet initial = new SimpleTweet("inital Tweet", null);


        ReTweet reTweet = service.reTweet(null, "first retweet", initial);
        for (int i = 0; i < 10; i++) {
            reTweet  = service.reTweet(null, i + " retweet", reTweet);
        }

        service.like(null, reTweet);
        int actual = initial.getLikeCount();
        System.out.println(reTweet);
        assertEquals(expected,actual);
    }
}

