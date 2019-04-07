package com.hcyzzl.mks.backend.common.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.IOException;

/**
 * @Author chendong
 * @create 2019/4/7 18:34
 */
public class LongSerializer extends JsonSerializer<Long> {
    /**
     * js 中数值的最大值
     */
    private static final long JS_NUMBER_MAX = 9007199254740992L;

    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // 如果 Long 数值过大，则转换为 String 序列化，否则仍然按照数字进行序列化
        // 主要考虑到可能会有像 page.total 这种明明是 Long 类型，但值却不会很大的情况
        if (value > JS_NUMBER_MAX || value < -JS_NUMBER_MAX) {
            ToStringSerializer.instance.serialize(value, gen, serializers);
            return;
        }
        gen.writeNumber(value);
    }
}
