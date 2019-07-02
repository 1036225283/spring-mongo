package xws.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import xws.entity.User;

import java.util.List;

/**
 * Created by xws on 2019/7/3.
 */
public interface UserRepository extends MongoRepository<User, Integer> {


    @Query(value = "{'roleList.$id':?0}", fields = "{ '_id' : 1, 'name' : 1}")
    List<User> findByRole(ObjectId roleId);

}
