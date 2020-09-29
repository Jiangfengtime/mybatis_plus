package com.hjf.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Delete;

import java.util.Date;

/**
 * @author Jiang锋时刻
 * @create 2020-09-28 15:46
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * IdType.AUTO: 数据库ID自增 (数据库字段一定要设置为自增的, 否则会报错)
     * IdType.NONE: 未设置主键
     * IdType.INPUT: 手动输入
     * IdType.ID_WORKER: 默认的全局ID(数字)
     * IdType.UUID: 全局唯一ID uuid
     * IdType.ID_WORKER_STR: ID_WORKER的字符串(字符串)
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    // 字段添加填充内容
    // 插入的时候自动操作
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    // 插入或更新的时候自动操作
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version //乐观锁Version注解
    private Integer version;

    @TableLogic // 逻辑删除注解
    private Integer deleted;
}
