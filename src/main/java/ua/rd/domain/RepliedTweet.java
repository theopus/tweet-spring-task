package ua.rd.domain;

/**
 * Created by irina on 23.09.17.
 */
public class RepliedTweet extends Tweet {

    private Tweet repliedTo;

    public RepliedTweet(User repliedUser, String msg, Tweet repliedTo) {
        super(msg, repliedUser);
        this.repliedTo = repliedTo;
    }

    public Tweet getRepliedTo() {
        return repliedTo;
    }


    @Override
    public String toString() {
        return super.toString() + "=-=RepliedTweet{" +
                "repliedTo=" + repliedTo +
                '}';
    }
}
