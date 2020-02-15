package com.bob.common.utils;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ProtoStuffUtils {

    private static Map<Class<?> , Schema<?>> schemaCache;

    static{
        schemaCache = new ConcurrentHashMap<>();
    }

    private static <T>  Schema<T> getSchema(Class<T> cls){
        Schema<T> schema = null;
        if(schemaCache.containsKey(cls)){
            schema = (Schema<T>) schemaCache.get(cls);
        }else{
            schema = RuntimeSchema.createFrom(cls);
            schemaCache.put(cls, schema);
        }
        return schema;
    }

    public static <T> byte[] serialize(T t){
        Schema<T> schema = getSchema((Class<T>) t.getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        byte[] bytes = ProtostuffIOUtil.toByteArray(t, schema, buffer);
        buffer.clear();
        return bytes;
    }

    public static <T> T deserialize(byte[] bytes, Class<T> cls) throws IllegalAccessException, InstantiationException {
        Schema<T> schema = getSchema(cls);
        T obj = cls.newInstance();
        ProtostuffIOUtil.mergeFrom(bytes, obj, schema);
        return obj;
    }

}
