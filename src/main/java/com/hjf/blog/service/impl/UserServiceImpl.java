package com.hjf.blog.service.impl;

import com.hjf.blog.entity.User;
import com.hjf.blog.mapper.UserMapper;
import com.hjf.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jiang锋时刻
 * @since 2020-09-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
