package com.learn.mybatisplusstage01;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.mybatisplusstage01.entity.Product;
import com.learn.mybatisplusstage01.entity.User;
import com.learn.mybatisplusstage01.mapper.ProductMapper;
import com.learn.mybatisplusstage01.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
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
        user.setName("xiaoshigk");
        user.setEmail("xiaoshi@gmail.com");
        user.setAge(30);
        System.out.println(userMapper.insert(user) > 0 ? "success" : "failure");
    }

    @Test
    public void testUpdate() {
/*        // 根据id
        User user = userMapper.selectById(1);
        user.setName("约翰");
        int i = userMapper.updateById(user);
        System.out.println(i);*/

        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        User user = new User();
//        user.setName("约翰");
        updateWrapper.eq(User::getName, "xiaoshi");
        updateWrapper.set(User::getAge, 55);
        updateWrapper.set(User::getName, "方源");
//        updateWrapper.set(User::getCreatTime,new Date());
        userMapper.update(user, updateWrapper);
    }

    @Resource
    ProductMapper productMapper;

    @Test
    public void testUpdateConcurrent() {
        System.out.println("修改前" + productMapper.selectById(1));
        Product product1 = productMapper.selectById(1);
        Product product2 = productMapper.selectById(1);

        product1.setPrice(product1.getPrice() + 1000);
        productMapper.updateById(product1);

        product2.setPrice(product2.getPrice() - 500);
//        productMapper.updateById(product2);
        int i = productMapper.updateById(product2);
        while (i == 0) { //自旋转  失败时继续, 直到成功为止
            product2 = productMapper.selectById(1);
            product2.setPrice(product2.getPrice() - 500);
            i = productMapper.updateById(product2); // 接收执行返回的影响记录的条数，=0代表执行失败
        }

        System.out.println("修改后" + productMapper.selectById(1));

    }

    @Test
    public void testDel() {
//        System.out.println(userMapper.deleteById(5) > 0 ? "success" : "failure");
        userMapper.selectList(null);
        userMapper.deleteBatchIds(Arrays.asList(1, 2, 3, 4));

    }

    @Test
    public void testCustom() {
//        userMapper.selectUserByAge(20,50);

        //测试自定义带条件查询分页数据
        Page<User> page = new Page<>(1, 3);
        List<User> users = userMapper.selectPageByCondition(
                page, new QueryWrapper<User>()
                .ge("age", 20)
                .le("age", 50)
        );
        page.setRecords(users);
    }
}
