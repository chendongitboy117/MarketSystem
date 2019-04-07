package com.hcyzzl.mks.backend.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hcyzzl.mks.backend.common.parse.CustomLocalDateEditor;
import com.hcyzzl.mks.backend.common.parse.CustomLocalDateTimeEditor;
import com.hcyzzl.mks.backend.common.parse.CustomLocalTimeEditor;
import com.hcyzzl.mks.backend.common.serialize.LongSerializer;
import com.hcyzzl.mks.common.utils.LocalDateTimeFormatUtil;

import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @Author chendong
 * @create 2019/4/7 18:14
 */
public final class SingleObjectUtil {
    public static final CustomLocalDateTimeEditor CUSTOM_LOCAL_DATE_TIME_EDITOR = new CustomLocalDateTimeEditor();
    public static final CustomLocalDateEditor CUSTOM_LOCAL_DATE_EDITOR = new CustomLocalDateEditor();
    public static final CustomLocalTimeEditor CUSTOM_LOCAL_TIME_EDITOR = new CustomLocalTimeEditor();
    /**
     * 提供一个全局可用的序列化 Bean
     */
    public static final ObjectMapper OM = new ObjectMapper()
            //Date 对象的格式
            .setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS"))
            //默认忽略值为 null 的属性
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            //禁止序列化时间为时间戳
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            //启用序列化 BigDecimal 为非科学计算法格数
            .enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)
            .registerModules(
                    //注册 Jsr310（Java8 的时间兼容模块）
                    new JavaTimeModule(),
                    //序列化 Long 为 String
                    new SimpleModule()
                            //大数字直接序列化为 String
                            .addSerializer(Long.class, new LongSerializer())
                            .addSerializer(long.class, new LongSerializer())
                            .addSerializer(Long.TYPE, new LongSerializer())
                            .addSerializer(BigInteger.class, ToStringSerializer.instance)
                            .addDeserializer(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                                @Override
                                public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                                    final String str = p.getValueAsString();
                                    return LocalDateTimeFormatUtil.parseLocalDateTime(str);
                                }
                            })
                            .addDeserializer(LocalDate.class, new JsonDeserializer<LocalDate>() {
                                @Override
                                public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                                    final String str = p.getValueAsString();
                                    return LocalDateTimeFormatUtil.parseLocalDate(str);
                                }
                            })
                            .addDeserializer(LocalTime.class, new JsonDeserializer<LocalTime>() {
                                @Override
                                public LocalTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
                                    final String str = p.getValueAsString();
                                    return LocalDateTimeFormatUtil.parseLocalTime(str);
                                }
                            })
            )
            // JSON 序列化移除 transient 修饰的 Page 无关紧要的返回属性
            .configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private SingleObjectUtil() {
    }
}
