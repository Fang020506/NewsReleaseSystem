package com.news.mapper;

import com.news.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Sancean
 * @since 2023-05-18
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    User getUserByName(String name);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    List<User> getAllUsers();

    List<User> getUsersByLike(String key);

    User getUserById(int id);
}
