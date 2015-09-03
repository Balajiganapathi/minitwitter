package minitwitter.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by deepap on 3/9/15.
 */
@Entity
@Table
public class Followers {
    private TUser follower;
    private TUser followee;

    protected Followers() {}

    public Followers(TUser follower, TUser followee) {
        this.follower = follower;
        this.followee = followee;
    }

    public TUser getFollowee() {
        return followee;
    }

    public TUser getFollower() {
        return follower;
    }

}
