package ua.rd.service;

import org.springframework.stereotype.Service;
import ua.rd.domain.*;
import ua.rd.repository.TweetRepository;

/**
 * Created by irina on 23.09.17.
 */
@Service
public class TweetServiceImpl implements TweetService {

    private final TweetFactory factory;
    private TweetRepository repository;

    public TweetServiceImpl(TweetFactory factory, TweetRepository repository) {
        this.factory = factory;
        this.repository = repository;
    }

    public Tweet tweet(User user, String txt) {
        Tweet tweet = factory.simpleTweet(user, txt);
        repository.saveTweet(tweet);
        return tweet;
    }

    public Tweet like(User user, Tweet tweet) {
        int likesCount = tweet.getLikeCount();
        tweet.incLikeCount();
        return tweet;
    }

    public ReTweet reTweet(User user, String txt, Tweet tweet){
        ReTweet reTweet = factory.reTweet(user, txt, tweet);
        repository.saveTweet(reTweet);
        return reTweet;
    }

    public RepliedTweet reply(User user, String txt, Tweet tweet) {
        RepliedTweet repliedTweet = factory.replyTweet(user, txt, tweet);
        repository.saveTweet(repliedTweet);
        return repliedTweet;
    }


}
