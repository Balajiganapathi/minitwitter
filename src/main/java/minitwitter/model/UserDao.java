package minitwitter.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by deepap on 27/8/15.
 */
public interface UserDao extends CrudRepository<TUser, Long>{
    TUser findById(Long Id);
    TUser findByName(String name);
}
