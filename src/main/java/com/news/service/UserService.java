package com.news.service;

import com.news.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpSession;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sancean
 * @since 2023-05-18
 */
public interface UserService extends IService<User> {
    // 登录验证
    User login(String name, String password);

    // 注册用户
    void register(User user) throws Exception;

    // 通过用户名查询用户
    User selectByName(String name);
    // 删除用户
    void deleteUser(int id);

    // 查询所有用户
    List<User> selectAllUsers();

    // 模糊查询用户
    List<User> selectUsersByLike(String keywords);

    // 设置用户为管理员
    void setAdmin(int id);

    // 用户主动下线
    void userOffline(HttpSession session);
}

