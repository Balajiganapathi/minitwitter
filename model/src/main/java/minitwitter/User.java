package minitwitter;

import javax.persistence.*;

/**
 * Created by deepap on 27/8/15.
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
    private String password;

    protected User() {}

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

        @Override
    public String toString() {
        return String.format(
                "User[id=%d, name='%s', email= '%s']",
                id, name, email);
    }
}
