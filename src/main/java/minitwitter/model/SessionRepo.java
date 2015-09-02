package minitwitter.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by balajiganapathise on 2/9/15.
 */
public interface SessionRepo extends CrudRepository<Session, Long> {
    Session findBySessionId(long id);
    Session findByUser(TUser user);
}
