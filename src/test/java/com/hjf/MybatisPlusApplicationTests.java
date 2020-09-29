package com.hjf;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjf.mapper.UserMapper;
import com.hjf.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {
    /**
     * 继承了BaseMapper, 所有的放啊都来自自己的父类
     * 我们也可以编写自己的扩展方法
     */
    @Autowired
    private UserMapper userMapper;

    @Test
    void test01(){
//        User user = new User(8L, "user01", 23, "user01@qq.com");
//        userMapper.insert(user);
    }
    @Test
    void test02(){
        User user = new User();
        user.setName("user03");
        user.setAge(8);
        user.setEmail("user03@qq.com");
        userMapper.insert(user);
    }

    // 通过id更改
    @Test
    public void test03(){
        User user = new User();
        // 通过条件动态拼接SQL
        user.setId(6L);
        user.setName("user1");
        user.setEmail("user1@qq.com");
        // updateById的参数是一个对象
        userMapper.updateById(user);
    }

    // 测试乐观锁成功!
    @Test
    public void testOptimisticLocker1(){
        // 1. 查询用户信息
        User user = userMapper.selectById(6L);
        // 2. 修改用户信息
        user.setName("用户02");
        user.setEmail("user01@gmail.com");
        // 3. 执行更新操作
        userMapper.updateById(user);

    }
    // 测试乐观锁失败!
    @Test
    public void testOptimisticLocker2(){
        // 线程1
        User user1 = userMapper.selectById(7L);
        user1.setName("用户001");
        user1.setEmail("user001@gmail.com");
        // 模拟另一个线程执行了插队操作
        User user2 = userMapper.selectById(7L);
        user2.setName("用户002");
        user2.setEmail("user002@gmail.com");
        userMapper.updateById(user2);
        userMapper.updateById(user1);
    }

    // 通过id查询
    @Test
        public void testSelectById(){
            User user = userMapper.selectById(5L);
            System.out.println(user);
    }

    // id批量查询
    @Test
    public void testSelectBatchByIds(){
        List<User> userList = userMapper.selectBatchIds(Arrays.asList(5, 6, 7));
        userList.forEach(System.out::println);
    }

    // 按条件查询: map
    @Test
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
//        map.put("name", "用户002");
//        map.put("age", 8);
        map.put("deleted", 1);
        // SELECT * FROM user WHERE name = ? AND age = ?
        List<User> userList = userMapper.selectByMap(map);
        userList.forEach(System.out::println);
    }

    @Test
    public void testPage(){
        /**
         * 参数1: 当前页
         * 参数2: 页面大小
         */
        Page<User> page = new Page<>(2, 3);
        IPage<User> userIPage = userMapper.selectPage(page, null);
        System.out.println("------------------------");
        // 获取当前页信息
        page.getRecords().forEach(System.out::println);
        userIPage.getRecords().forEach(System.out::println);
        System.out.println("------------------------");
        // 获取数据总数
        System.out.println(page.getTotal());
        System.out.println(userIPage.getTotal());
    }

    // 根据id删除
    @Test
    public void testDeleteById(){
        userMapper.deleteById(5L);
    }
    // 批量参数
    @Test
    public void testDeleteBatchIds(){
        userMapper.deleteBatchIds(Arrays.asList(3,4));
    }

    @Test
    public void testDeleteByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "用户002");
        userMapper.deleteByMap(map);
    }

    @Test
    void test10() {
        // 查询全部的用户
        // selectList的参数是一个Wrapper, 条件构造器, 这里我们先不用: null
        List<User> userList = userMapper.selectList(null);
        // 遍历1
        for (User user: userList){
            System.out.println(user);
        }
        System.out.println("----------------------");
        // 遍历2
        userList.forEach(System.out::println);
    }
}
