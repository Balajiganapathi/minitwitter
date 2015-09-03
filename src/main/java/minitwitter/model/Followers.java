package minitwitter.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by deepap on 3/9/15.
 */
@Entity
public class Followers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private TUser follower;
    @ManyToOne
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

    @JsonIgnore
    public Long getId() {
        return id;
    }
}
