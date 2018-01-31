package com.holdon.user.config;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.support.springfox.SwaggerJsonSerializer;
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by wd on 2018/1/30.
 */
public class FastJson2SwaggeSerializer implements ObjectSerializer, ObjectDeserializer {

    public final static SwaggerJsonSerializer instance = new SwaggerJsonSerializer();

    @Override
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
        return null;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }

    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) throws IOException {
        SerializeWriter out = jsonSerializer.getWriter();
        Json json = (Json) o;
        out.write(json.value());
    }
}
