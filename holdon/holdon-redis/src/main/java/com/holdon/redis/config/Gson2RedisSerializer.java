package com.holdon.redis.config;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * Created by wd on 2018/1/29.
 */
public class Gson2RedisSerializer<T> implements RedisSerializer<T> {
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private Class<T> classT;
    private TypeToken typeToken;
    private Gson gson;

    public Gson2RedisSerializer(Gson gson) {
        this.gson = gson;
    }

    public Gson2RedisSerializer(Gson gson, TypeToken typeToken) {
        this.typeToken = typeToken;
        this.gson = gson;
    }

    public Gson2RedisSerializer(Gson gson, Class<T> classT) {
        this.classT = classT;
        this.gson = gson;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        } else {
            try {
                String json = gson.toJson(t);
                return json.getBytes();
            } catch (Exception var) {
                throw new SerializationException("Could not read JSON: " + var.getMessage(), var);
            }
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (null == bytes || bytes.length == 0) {
            return null;
        } else {
            try {
                Type type = new TypeToken<T>() {
                }.getType();
                type.getTypeName();
                return gson.fromJson(new String(bytes), type);
//                return gson.fromJson(new String(bytes), typeToken.getType());
            } catch (Exception e) {
                throw new SerializationException("Could not read JSON: " + e.getMessage(), e);
            }
        }
    }
//
//    public T deserialize(byte[] bytes) throws SerializationException {
//        if (null == bytes || bytes.length == 0) {
//            return null;
//        } else {
//            try {
//                String json = new String(bytes);
//                LinkedTreeMap tm = gson.fromJson(json, LinkedTreeMap.class);
//                Iterator it = tm.keySet().iterator();
//                while (it.hasNext()) {
//                    String key = (String) it.next();
//                    String value = (String) tm.get(key);
//                }
//                return gson.fromJson(json,LinkedTreeMap.class.getGenericSuperclass());
//            } catch (Exception e) {
//                throw new SerializationException("Could not read JSON: " + e.getMessage(), e);
//            }
//        }
//    }
}