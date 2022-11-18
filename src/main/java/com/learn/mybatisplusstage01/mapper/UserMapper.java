package com.learn.mybatisplusstage01.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.mybatisplusstage01.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> selectUserByAge(@Param("beginAge") Integer beginAge ,
                               @Param("endAge") Integer endAge);

    //mbp自定义方法，如果传入了page对象并且配置了分页拦截器，他会自动使用分页条件拼接到sql后
    List<User> selectPageByCondition(Page<User> page , @Param("ew") QueryWrapper queryWrapper);
}