package ua.rd.domain;

/**
 * Created by irina on 23.09.17.
 */
public abstract class Tweet {
    private long id;

    private String txt;
    private User user;
    private int likeCount = 0;
    private int replyCount = 0;
    private int reTweetCount = 0;

    public Tweet(String txt, User user) {
        this.txt = txt;
        this.user = user;
    }

    public String getText(){
        return txt;
    }
    public User getUser(){
        return user;
    }
    public int getLikeCount(){
        return likeCount;
    }

    public int incLikeCount(){
        return ++likeCount;
    }

    public int getReplyCount(){
        return replyCount;
    }

    public int incReplyCount(){
        return ++replyCount;
    }

    public int getReTweetCount(){
        return reTweetCount;
    }

    public int incReTweetCount(){
        return ++reTweetCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", txt='" + txt + '\'' +
                ", user=" + user +
                ", likeCount=" + likeCount +
                ", replyCount=" + replyCount +
                ", reTweetCount=" + reTweetCount +
                '}';
    }
}
