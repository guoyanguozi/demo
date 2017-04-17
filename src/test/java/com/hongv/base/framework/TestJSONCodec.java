package com.hongv.base.framework;

import ch.qos.logback.ext.spring.web.LogbackConfigListener;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hongv.demo.base.framework.codec.JSONCodec;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hongwei on 2017/4/18.
 */
public class TestJSONCodec {

    @Test
    public void testJSONCodec_Encode() {
        TestModel joe = new TestModel(22, "Joe");

        Logger logger = LoggerFactory.getLogger(getClass());

        String json = JSONCodec.encode(joe);
        logger.info("test model to json :  {}", json);

        Assert.assertEquals("{\"age\":22,\"name\":\"Joe\"}", json);
    }

    @Test
    public void testJSONCodec_Decode() {
        Logger logger = LoggerFactory.getLogger(getClass());

        String json = "{\"age\":22,\"name\":\"Joe\"}";
        TestModel model = JSONCodec.decode(json, TestModel.class);
        logger.info("json to test model :  {}", model);

        Assert.assertEquals(model.getAge(), 22);
        Assert.assertEquals(model.getName(), "Joe");
    }
}
