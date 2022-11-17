package com.learn.mybatisplusstage01;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.mybatisplusstage01.entity.User;
import com.learn.mybatisplusstage01.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class MybatisPlusStage01ApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void contextLoads() {

    }

    @Test
    public void testSelectAll() {
        // 查询所有
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectByConditions() {
        // 条件查询
/*        User user = userMapper.selectById(1);
        System.out.println(user);*/
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
/*        queryWrapper.eq("name","Sandy");
        System.out.println(userMapper.selectOne(queryWrapper));*/
/*        queryWrapper.gt("age",20);
        queryWrapper.lt("age",25);
        System.out.println(userMapper.selectList(queryWrapper));*/
// 使用lambda
/*        System.out.println(userMapper.selectList(new LambdaQueryWrapper<User>()
                .gt(User::getAge, 20)
                .lt(User::getAge, 25)
                .like(User::getName, "a")
                .orderByDesc(User::getAge)
                .select(User::getName, User::getAge)
        ));*/

        System.out.println(userMapper.selectList(new LambdaQueryWrapper<User>()
                .gt(User::getAge, 18)
                .and(w -> w
                        .eq(User::getAge, 28)
                        .or()
                        .like(User::getName, "o")
                )
        ));
    }

    @Test
    public void testPage() {
        //分页查询
/*        System.out.println(userMapper.selectList(new LambdaQueryWrapper<User>()
                .last("limit 0,2")
        ));*/
        IPage<User> page = new Page<>(2, 2);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.gt(User::getAge, 20);
        userMapper.selectPage(page, wrapper);

        System.out.println("查询到的记录：" + page.getRecords());
        System.out.println("查询到的总记录数：" + page.getTotal());
        System.out.println("查询到的总页码：" + page.getPages());
    }

    // 新增
    @Test
    public void testInsert() {
        User user = new User();
        user.setName("xiaoshi");
        user.setEmail("xiaoshi@gmail.com");
        user.setAge(30);
        System.out.println(userMapper.insert(user)>0 ? "success" : "failure");
    }



}
