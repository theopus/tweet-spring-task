package ua.rd.service;

import org.springframework.stereotype.Service;
import ua.rd.domain.*;

/**
 * Created by irina on 24.09.17.
 */

public interface TweetService {

    Tweet tweet(User user, @TweetSize String txt);
    ReTweet reTweet(User user, @TweetSize String txt, Tweet tweet);
    RepliedTweet reply(User user, @TweetSize String txt, Tweet tweet);
    Tweet like(User user, Tweet tweet);
}
