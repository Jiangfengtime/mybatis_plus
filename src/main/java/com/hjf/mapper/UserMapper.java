package com.hjf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjf.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author Jiang锋时刻
 * @create 2020-09-28 15:48
 */
// 代表持久层
@Repository
// 在对应的Mapper上面继承基本的接口: BaseMapper
public interface UserMapper extends BaseMapper<User> {
    // 所有的CRUD操作都已经编写完成了
    // 不需要像之前的一样配置一大堆文件
}
