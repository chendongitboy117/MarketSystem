package com.hcyzzl.mks.backend.common.parse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyEditorSupport;
import java.time.temporal.Temporal;
import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author chendong
 * @create 2019/4/7 18:17
 */
public abstract class BaseCustomDateTimeEditor<T extends Temporal> extends PropertyEditorSupport {
    protected static final Logger log = LoggerFactory.getLogger(BaseCustomDateTimeEditor.class);
    private final Function<String, T> converter;
    private final boolean allowEmpty;

    public BaseCustomDateTimeEditor(boolean allowEmpty, Function<String, T> converter) {
        this.allowEmpty = allowEmpty;
        this.converter = converter;
    }

    public BaseCustomDateTimeEditor(Function<String, T> converter) {
        this(true, converter);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (allowEmpty && StringUtils.isBlank(text)) {
            setValue(null);
            return;
        }
        try {
            setValue(this.converter.apply(text));
        } catch (Exception e) {
            throw new IllegalArgumentException("Try parse date failed: " + e.getMessage(), e);
        }
    }
}
