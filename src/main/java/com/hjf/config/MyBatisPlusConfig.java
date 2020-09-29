package com.hjf.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Jiang锋时刻
 * @create 2020-09-29 10:25
 */

// 可以将主启动类上面的MapperScan注解, 添加到这里
@MapperScan("com.hjf.mapper")   // 扫描mapper文件夹
@EnableTransactionManagement    // 自动开启事务的注解
@Configuration  // 配置类
public class MyBatisPlusConfig {
    // 注册乐观锁插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    // 注册分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    // 逻辑删除组件
    @Bean
    public ISqlInjector iSqlInjector(){
        return new LogicSqlInjector();
    }

    // SQL执行效率插件
    @Bean
    // 设置dev 和test环境开启, 保证我们的效率
    @Profile({"dev", "test"})
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        // 单位: ms. 设置sql执行的最大时间, 如果错过就不执行
        performanceInterceptor.setMaxTime(1000);
        // 是否开启格式化
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
}
