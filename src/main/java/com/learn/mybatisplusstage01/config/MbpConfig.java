package com.learn.mybatisplusstage01.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MbpConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor (){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        //设置分页合理化： 如果查询的页码>总页码 返回最后一页数据，如果查询页码<1返回第一页数据
        paginationInnerInterceptor.setOverflow(true);
        paginationInnerInterceptor.setDbType(DbType.MYSQL);

        // 分页
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        //乐观锁
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }
}
