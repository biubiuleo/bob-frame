package com.bob.common.utils;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName BeanCopierUtils
 * @Description The bean tools from cglib
 * @Author bob
 * @Date 2018/6/28 11:39
 * @Version 1.0
 */
public final class BeanCopierUtils {

   private static Map<Integer, BeanCopier> beanCopierCache = null;

    static{
        beanCopierCache =  new ConcurrentHashMap<Integer, BeanCopier>();
    }

    private static  <S, T> BeanCopier getBeanCopier(S source , T target, boolean useConverter){
        int sCode = source.getClass().getName().hashCode();
        int tCode = target.getClass().getName().hashCode();
        int key = sCode+tCode+30;
        BeanCopier copier = null;
        if(!beanCopierCache.containsKey(key)){
            copier = BeanCopier.create(source.getClass(), target.getClass(), useConverter);
            beanCopierCache.put(key, copier);
        }else{
            copier = beanCopierCache.get(key);
        }
        return copier;
    }

    /**
     * The bean copy
     * @param source the source object of copy
     * @param target the target object of copy
     * @param <S> the source generic
     * @param <T> the target generic
     */
    public static <S, T> void copy(S source, T target){
        BeanCopier copier = getBeanCopier(source, target, false);
        copier.copy(source,target,null);
    }

    /**
     * The bean copy by converter
     * @param source  the source object of copy
     * @param target  the target object of copy
     * @param converter the bean copier from converter
     * @param <S> the source generic
     * @param <T> the target generic
     */
    public static <S,T> void converterCopy(S source, T target, Converter converter){
        BeanCopier copier = getBeanCopier(source, target, true);
        copier.copy(source,target,converter);
    }

}
