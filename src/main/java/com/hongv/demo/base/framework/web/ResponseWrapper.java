package com.hongv.demo.base.framework.web;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hongwei on 2017/4/18.
 */
public class ResponseWrapper {
    //    这次请求的响应状态， 可以自己定义， 比如返回 200401 表示请求有controller成功处理，但是用户不合法
    private final int status;
    // 这次请求server返回的描述信息（可有可无，出错的时候 可能是描述错误的信息，就比如 status返回200401， message 返回 用户不合法）
    private String message;
    // 这次请求的实际返回内容， 在出错情况下返回空或者不返回，这些都是根据具体的业务去定义的
    private final Map<String, Object> data = new HashMap<>();

    public static ResponseWrapper EMPTY = new ResponseWrapper() {
        @Override
        public ResponseWrapper addObject(String key, Object value) {
            throw new UnsupportedOperationException();
        }
    };

    public ResponseWrapper() {
        this.status = 200;
        this.message = null;
    }

    public ResponseWrapper(int status) {
        this.status = status;
        this.message = null;
    }

    public ResponseWrapper(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public ResponseWrapper addObject(String key, Object value) {
        if (key != null) {
            data.put(key, value);
        }
        return this;
    }

}

