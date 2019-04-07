package com.hcyzzl.mks.common.ref.copy;

import java.util.function.Function;

/**
 * 拷贝那一个字段
 * @Author chendong
 * @create 2019/4/7 17:33
 */
public class BeanCopyField {
    private String from;
    private String to;
    private Function<? super Object, ? super Object> converter;

    public BeanCopyField() {
    }

    public BeanCopyField(String from, String to, Function<? super Object, ? super Object> converter) {
        this.from = from;
        this.to = to;
        this.converter = converter;
    }

    public String getFrom() {
        return from;
    }

    public BeanCopyField setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public BeanCopyField setTo(String to) {
        this.to = to;
        return this;
    }

    public Function<? super Object, ? super Object> getConverter() {
        return converter;
    }

    public BeanCopyField setConverter(Function<? super Object, ? super Object> converter) {
        this.converter = converter;
        return this;
    }
}

