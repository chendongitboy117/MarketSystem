package com.hcyzzl.mks.backend.common.parse;

import com.hcyzzl.mks.common.utils.LocalDateTimeFormatUtil;

import java.time.LocalDateTime;

/**
 * @Author chendong
 * @create 2019/4/7 18:15
 */
public class CustomLocalDateTimeEditor extends BaseCustomDateTimeEditor<LocalDateTime> {
    public CustomLocalDateTimeEditor() {
        super(LocalDateTimeFormatUtil::parseLocalDateTime);
    }
}
