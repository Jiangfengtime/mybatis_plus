package com.hjf;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjf.mapper.UserMapper;
import com.hjf.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.lang.model.element.VariableElement;
import java.util.List;

/**
 * @author Jiang锋时刻
 * @create 2020-09-29 14:26
 */
@SpringBootTest
public class MapperTest {
    @Autowired
    private UserMapper userMapper;

    // 查询name 和 email不为空, 且 age > 15的用户
    @Test
    public void test01(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name")
                .isNotNull("email")
                .gt("age", 15);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    public void test02(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "Jack");
        // selectOne: 查询一个数据
        System.out.println(userMapper.selectOne(wrapper));
    }

    // 查询年龄在16-20之间的用户数量
    @Test
    public void test03(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        /**
         * between: 查询区间内的数据: [左闭右闭]
         * selectCount: 统计数量
         */
        wrapper.between("age", 16, 20);
        // selectOne: 查询一个数据
        System.out.println(userMapper.selectCount(wrapper));
    }

    // 模糊查询
    @Test
    public void test04(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        /**
         * notLike: 不包含指定元素
         * likeLeft: 以xx结尾
         * likeRight: 以xx开头
         */
        wrapper.notLike("name", "e")
                .likeLeft("name", "k")
                .likeRight("email", "t");

        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    // 子查询
    @Test
    public void test05(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("id", "select id from user where id < 3");
        List<Object> list = userMapper.selectObjs(wrapper);
        list.forEach(System.out::println);
    }

    // 排序查询
    @Test
    public void test06(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }
}
