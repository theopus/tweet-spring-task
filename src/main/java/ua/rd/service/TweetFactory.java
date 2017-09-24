package ua.rd.service;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;
import ua.rd.domain.ReTweet;
import ua.rd.domain.RepliedTweet;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;

/**
 * Created by irina on 24.09.17.
 */
@Component
public class TweetFactory {

    @Lookup(value = "replyTweet")
    public RepliedTweet replyTweet(User user, String txt, Tweet tweet){
        return null;
    }
    @Lookup(value = "simpleTweet")
    public Tweet simpleTweet(User user, String message){
        return null;
    }
    @Lookup(value = "reTweet")
    public ReTweet reTweet(User user, String txt, Tweet tweet){
        return null;
    }
}
