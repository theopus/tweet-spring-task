package ua.rd.service;

/**
 * Created by irina on 24.09.17.
 */
public class TweetSizeException extends RuntimeException {
    public TweetSizeException(String tweetSize) {
        super(tweetSize);
    }
}
