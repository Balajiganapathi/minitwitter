package minitwitter.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by balajiganapathise on 3/9/15.
 */
public interface TweetRepo extends CrudRepository<Tweet, Long> {
    Tweet findByTweetId(long tweetId);
    List<Tweet> findByUser(TUser user);
}
