package minitwitter.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by deepap on 3/9/15.
 */
public interface FollowersRepo extends CrudRepository<Followers, Long>{
    List<Followers> findByFollower(TUser follower);
}
