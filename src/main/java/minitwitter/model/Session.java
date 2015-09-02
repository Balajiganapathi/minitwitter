package minitwitter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by balajiganapathise on 2/9/15.
 */
@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sessionId;

    @OneToOne
    @NotNull
    private TUser user;

    public long getSessionId() {
        return sessionId;
    }

    public TUser getUser() {
        return user;
    }

    protected Session() {}
    public Session(TUser user) {
        this.user = user;
    }
}
