package ua.rd.domain;

/**
 * Created by irina on 23.09.17.
 */
public class User {

    private long id;
    private String nickname;

    public User(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public long getId() {
        return id;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }
}
