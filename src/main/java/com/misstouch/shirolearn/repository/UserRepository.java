package com.misstouch.shirolearn.repository;

import com.misstouch.shirolearn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hechao
 * @date create in 12:42 2018/5/8/008
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    /**
     * 使用用户名查询用户信息
     * @param username 用户名
     * @return
     */
    User findByUsername(String username);

}
