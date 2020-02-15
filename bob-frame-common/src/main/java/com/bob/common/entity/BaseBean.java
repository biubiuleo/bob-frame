package com.bob.common.entity;


import com.alibaba.fastjson.JSON;
import com.bob.common.utils.BeanCopierUtils;
import com.bob.common.utils.ProtoStuffUtils;
import net.sf.cglib.core.Converter;

import java.io.Serializable;

/**
 * @ClassName BaseBean
 * @Description TODO
 * @Author bob
 * @Date 2018/7/12 14:31
 * @Version 1.0
 */
public abstract class BaseBean implements Serializable {

    /**
     * The json string from current object
     * @return the json string from current object
     */
    public String toJSON(){
        return JSON.toJSONString(this);
    }

    /**
     * The current object copy from target object
     * @param t the target object of copy
     * @param <T> the target generic
     */
    public <T> void copy(T t){
        BeanCopierUtils.copy( t, this);
    }

    /**
     * The current object copy to target object
     * @param t the target object of copy
     * @param <T> the target generic
     */
    public <T> void copyTo(T t){
        BeanCopierUtils.copy(this, t);
    }

    /**
     * The current object copy from target object by converter
     * @param t the target object of copy
     * @param converter the converter of copy
     * @param <T> the target generic
     */
    public <T> void copy(T t, Converter converter){
        BeanCopierUtils.converterCopy(t, this, converter);
    }

    /**
     * The current object copy to target object by converter
     * @param t  the target object of copy
     * @param converter the converter of copy
     * @param <T> the target generic
     */
    public <T> void  copyTo(T t, Converter converter){
        BeanCopierUtils.converterCopy(this, t, converter);
    }

    /**
     * The serialize from current object
     * @return the bytes from current object
     */
    public byte[] serialize(){
        return ProtoStuffUtils.serialize(this);
    }
}
