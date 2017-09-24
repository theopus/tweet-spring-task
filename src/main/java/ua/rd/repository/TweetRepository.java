package ua.rd.repository;

import ua.rd.domain.Tweet;
import ua.rd.domain.User;

/**
 * Created by irina on 24.09.17.
 */
public interface TweetRepository {

    Iterable<Tweet> getAll();
    Iterable<Tweet> getAllOwnedBy(User user);
    Tweet saveTweet(Tweet tweet);
    int getCount();

}
