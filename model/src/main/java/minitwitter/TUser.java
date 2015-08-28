package minitwitter;

import javax.persistence.*;

/**
 * Created by deepap on 27/8/15.
 */
@Entity
public class TUser {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
    private String password;

    protected TUser() {}

    public TUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format(
                "TUser[id=%d, name='%s', email= '%s']",
                id, name, email);
    }

        public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
