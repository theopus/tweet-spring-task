package ua.rd.domain;

/**
 * Created by irina on 24.09.17.
 */
public class ReTweet extends Tweet {

    private Tweet reTweeted;

    public ReTweet(String txt, User user, Tweet reTweeted) {
        super(txt, user);
        this.reTweeted = reTweeted;
    }

    @Override
    public int getLikeCount() {
        return reTweeted.getLikeCount();
    }

    @Override
    public int incLikeCount() {
        return reTweeted.incLikeCount();
    }

    @Override
    public int getReplyCount() {
        return super.getReplyCount() + reTweeted.getReplyCount();
    }

    @Override
    public int incReTweetCount() {
        return reTweeted.incReTweetCount();
    }

    @Override
    public int getReTweetCount() {
        return reTweeted.getReTweetCount();
    }

    public Tweet getReTweeted() {
        return reTweeted;
    }


    @Override
    public String toString() {
        return super.toString() + "=-=ReTweet{" +
                "reTweeted=" + reTweeted +
                '}';
    }
}
