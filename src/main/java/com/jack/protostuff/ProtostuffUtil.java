package com.jack.protostuff;


import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProtostuffUtil {

    private Map<Class, Schema> schemas = new ConcurrentHashMap();
    private static final Object lock = new Object();

    protected  Schema getSchema(Class clazz) {
        if(!this.schemas.containsKey(clazz)) {
            Schema schema = null;
            Object var3 = lock;
            synchronized(var3) {
                schema = RuntimeSchema.getSchema(clazz);
            }
            this.schemas.put(clazz, schema);
        }
        return (Schema)this.schemas.get(clazz);
    }
    public  <T> byte[] serializer(T o, Class<T> clazz) {
        Schema schema = getSchema(clazz);
        getSchema(Point.class);
        LinkedBuffer allocate = LinkedBuffer.allocate(512);
        byte[] bytes = ProtostuffIOUtil.toByteArray(o, schema, allocate);
        allocate.clear();
        return bytes;
    }

    public  <T> T deserializer(byte[] bytes, Class<T> clazz) {
            Schema<T> schema = this.getSchema(clazz);
            T t = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, t, schema);
            return t;

//        Schema<T> schema = this.getSchema(clazz);
//        T t = schema.newMessage();
//        ProtobufIOUtil.mergeFrom(bytes, t, schema);
//        return t;

    }
}
