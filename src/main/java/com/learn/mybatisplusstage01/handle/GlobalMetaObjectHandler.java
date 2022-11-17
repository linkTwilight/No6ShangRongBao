package com.learn.mybatisplusstage01.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class GlobalMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter("creatTime")){
            metaObject.setValue("creatTime",new Date());
        }
        if (metaObject.hasSetter("updateTime")){
            metaObject.setValue("updateTime",new Date());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter("updateTime")){
            metaObject.setValue("updateTime",new Date());
        }
    }
}
