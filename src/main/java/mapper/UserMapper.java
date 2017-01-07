package mapper;

import entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/1/7.
 */
public interface UserMapper {

    public User getUser(Integer id);

    public List<User> findByUserName(String name);


}
