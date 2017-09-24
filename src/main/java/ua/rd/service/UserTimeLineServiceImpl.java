package ua.rd.service;

import org.springframework.stereotype.Service;
import ua.rd.domain.RepliedTweet;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.repository.TweetRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by irina on 24.09.17.
 */
@Service
public class UserTimeLineServiceImpl implements UserTimeLineService {


    private TweetRepository repository;

    public UserTimeLineServiceImpl(TweetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Tweet> getUserTimeline(User user) {
        List<Tweet> result = new ArrayList<>();
        repository.getAll().forEach(tweet -> {
            if (tweet.getUser().equals(user)) {
                result.add(tweet);
            }
            else if (tweet instanceof RepliedTweet){
                Tweet repliedTo = ((RepliedTweet) tweet).getRepliedTo();
                if (repliedTo.getUser().equals(user))
                    result.add(tweet);
            }

        });
        return result;
    }
}
