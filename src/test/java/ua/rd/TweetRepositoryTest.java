package ua.rd;

import org.junit.Before;
import org.junit.Test;
import ua.rd.domain.RepliedTweet;
import ua.rd.domain.SimpleTweet;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.repository.InMemTweetReposoitory;
import ua.rd.repository.TweetRepository;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by irina on 24.09.17.
 */

public class TweetRepositoryTest {

    private TweetRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new InMemTweetReposoitory();
    }

    @Test
    public void addGet() throws Exception {
        int expect = 1;
        Tweet tweet = new SimpleTweet("tweet", null);
        repository.saveTweet(tweet);
        int actual = repository.getCount();

        assertEquals(expect, actual);

    }

    @Test
    public void addGetAll(){
        Iterable<Tweet> expexted = new ArrayList<Tweet>(){
            {
                add(new SimpleTweet(null,null));
                add(new RepliedTweet(null,"asd", null));
            }
        };

        expexted.forEach(tweet -> repository.saveTweet(tweet));

        Iterable<Tweet> actual = repository.getAll();

        assertEquals(expexted, actual);
    }

    @Test
    public void getOwnedBY() throws Exception {
        User user = new User("testUser");

        Iterable<Tweet> expected = new ArrayList<Tweet>(){
            {
                add(new SimpleTweet("test1", user));
                add(new SimpleTweet("test2",user));
            }
        };

        expected.forEach(tweet -> repository.saveTweet(tweet));
        repository.saveTweet(new SimpleTweet("testAnotherUser",new User("user2")));
        repository.saveTweet(new SimpleTweet("testAnotherUser",new User("user3")));

        Iterable<Tweet> actual = repository.getAllOwnedBy(user);

        assertEquals(expected,actual);
    }
}
