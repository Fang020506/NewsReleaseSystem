package com.news.service.impl;

import com.news.entity.User;
import com.news.mapper.UserMapper;
import com.news.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sancean
 * @since 2023-05-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;

    // 登录验证
    @Override
    public User login(String name, String password) {
        User user = userMapper.getUserByName(name);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    // 注册用户
    @Override
    public void register(User user) throws Exception {
        // 检查用户是否已存在
        User existingUser = userMapper.getUserByName(user.getName());
        if (existingUser != null) {
            throw new Exception("用户名已存在");
        }
        // 添加用户
        userMapper.addUser(user);
    }

    // 通过用户名查询用户
    @Override
    public User selectByName(String name) {
        return userMapper.getUserByName(name);
    }

    // 删除用户
    @Override
    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }

    // 查询所有用户
    @Override
    public List<User> selectAllUsers() {
        return userMapper.getAllUsers();
    }

    // 模糊查询用户
    @Override
    public List<User> selectUsersByLike(String keywords) {
        return userMapper.getUsersByLike(keywords);
    }

    // 设置用户为管理员
    @Override
    public void setAdmin(int id) {
        User user = userMapper.getUserById(id);
        user.setRole(1);  // 将该用户的 role 管理员属性设置为 1
        userMapper.updateUser(user);
    }



    @Override
    public void userOffline(HttpSession session) {
        session.removeAttribute("user");
    }
}
