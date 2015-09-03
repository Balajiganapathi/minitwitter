package minitwitter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by balajiganapathise on 3/9/15.
 */
@Entity
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tweetId;

    @NotNull
    private TUser user;

    @NotNull
    private String contents;

    public String getContents() {
        return contents;
    }

    public long getTweetId() {
        return tweetId;
    }

    public TUser getUser() {
        return user;
    }

    public Tweet() {}
    public Tweet(TUser user, String contents) {
        this.user = user;
        this.contents = contents;

    }
}
