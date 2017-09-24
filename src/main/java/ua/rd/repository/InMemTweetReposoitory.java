package ua.rd.repository;

import org.springframework.stereotype.Service;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by irina on 24.09.17.
 */
@Service
public class InMemTweetReposoitory implements TweetRepository{

    List<Tweet> tweets = new ArrayList<>();

    @Override
    public Iterable<Tweet> getAll() {
        return tweets;
    }

    @Override
    public Iterable<Tweet> getAllOwnedBy(User user) {
        return tweets.stream().filter(tweet -> tweet.getUser().equals(user)).collect(Collectors.toList());
    }

    @Override
    public Tweet saveTweet(Tweet tweet) {
        tweets.add(tweet);
        return tweet;
    }

    @Override
    public int getCount() {
        return tweets.size();
    }
}
