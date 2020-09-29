package com.hjf;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import java.util.ArrayList;

/**
 * @author Jiang锋时刻
 * @create 2020-09-29 20:06
 */
public class AutoCode {
    public static void main(String[] args) {
        // 1. 构建一个代码生成器对象
        AutoGenerator autoGenerator = new AutoGenerator();
        // 2. 配置策略
        GlobalConfig gc = new GlobalConfig();
        // 2.1 全局配置
        String projectPath = System.getProperty("user.dir");    // 项目路径
        gc.setOutputDir(projectPath + "/src/main/java");        //
        gc.setAuthor("Jiang锋时刻");   // 作者信息
        gc.setFileOverride(false); //是否覆盖原有的
        gc.setServiceName("%sService"); // 去Service的I前缀
        gc.setIdType(IdType.ID_WORKER); // Id生成的策略
        gc.setDateType(DateType.ONLY_DATE); // 日期生成策略
        gc.setSwagger2(true);
        autoGenerator.setGlobalConfig(gc);
        // 2.2 设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://www.jiangfengtime.top:3306/mybatis_plus?useSSL=false&useUnicode=true" +
                "&characterEncoding=utf-8&serverTimezone=UTC");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);
        autoGenerator.setDataSource(dsc);

        // 3. 包的设置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("blog");   //Module名
        pc.setParent("com.hjf");    // 包路径
        pc.setEntity("entity");     // 实体类
        pc.setService("service");
        pc.setController("controller");
        autoGenerator.setPackageInfo(pc);

        // 4. 策略配置
        StrategyConfig sc = new StrategyConfig();
        sc.setInclude("user"); // 需要映射的表名[重点]
        sc.setNaming(NamingStrategy.underline_to_camel); // 下划线转驼峰命名
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        // 父类实体
        // sc.setSuperEntityClass("");
        sc.setEntityLombokModel(true);
        sc.setRestControllerStyle(true);

        sc.setLogicDeleteFieldName("deleted"); // 逻辑删除字段

        // 自动填充配置
        // 创建时的策略
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        // 修改时的策略
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(updateTime);
        sc.setTableFillList(tableFills);

        // 乐观锁
        sc.setVersionFieldName("version");

        sc.setRestControllerStyle(true);    // 开启驼峰命名规则
        sc.setControllerMappingHyphenStyle(true); // 下划线链接: localhost:8080/hello_id_2

        autoGenerator.setStrategy(sc);

        autoGenerator.execute();

    }
}
