package ua.rd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ua.rd.domain.*;
import ua.rd.service.TweetFactory;

/**
 * Created by irina on 23.09.17.
 */
@Configuration
public class TweetsConfig {

    private long tweetCount = 0;

    @Bean
    @Scope("prototype")
    public Tweet simpleTweet(User user, String text){
        Tweet tweet = new SimpleTweet(text,user);
        tweet.setId(tweetCount++);
        return tweet;
    }

    @Bean
    @Scope("prototype")
    public RepliedTweet replyTweet(User user, String text, Tweet repliedTo){
        int replyCount = repliedTo.getReplyCount();
        repliedTo.incReplyCount();
        RepliedTweet repliedTweet = new RepliedTweet(user, text, repliedTo);
        repliedTweet.setId(tweetCount++);
        return repliedTweet;
    }

    @Bean
    @Scope("prototype")
    public ReTweet reTweet(User user, String text, Tweet reTweetTo){
        reTweetTo.incReTweetCount();
        if (reTweetTo instanceof ReTweet){
            ReTweet rTtRt = (ReTweet) reTweetTo;
            ReTweet reTweet = new ReTweet(text, user, rTtRt.getReTweeted());
            reTweet.setId(tweetCount++);
            return reTweet;
        }
        ReTweet reTweet = new ReTweet(text, user, reTweetTo);
        reTweet.setId(tweetCount++);
        return reTweet;

    }

    @Bean
    @Scope("singleton")
    public TweetFactory tweetFactory(){
        return new TweetFactory();
    }
}
