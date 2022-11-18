package com.learn.mybatisplusstage01.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
//    private String id;
    private String name;
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT) //新增时触发
    private Date creatTime;
//    @TableField(fill = FieldFill.UPDATE)//更新时触发
    @TableField(fill = FieldFill.INSERT_UPDATE)//新增和更新时触发
    private Date updateTime;

//    private Boolean isDeleted; //不能用is开头
    @TableField("is_deleted")
    @TableLogic // 表明当前属性是逻辑删除字段  查询时会自动使用该属性=0查询未删除的数据，删除时会自动更新该属性值为1
    private Boolean deleted;


}