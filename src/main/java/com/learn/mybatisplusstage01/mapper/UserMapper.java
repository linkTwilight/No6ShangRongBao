package com.learn.mybatisplusstage01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.learn.mybatisplusstage01.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> selectUserByAge(@Param("beginAge") Integer beginAge ,
                               @Param("endAge") Integer endAge);


}