package ua.rd.service;

import ua.rd.domain.Tweet;
import ua.rd.domain.User;

/**
 * Created by irina on 24.09.17.
 */
public interface UserTimeLineService {
    Iterable<Tweet> getUserTimeline(User user);
}
