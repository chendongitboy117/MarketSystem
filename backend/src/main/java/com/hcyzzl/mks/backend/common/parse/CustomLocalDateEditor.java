package com.hcyzzl.mks.backend.common.parse;

import com.hcyzzl.mks.common.utils.LocalDateTimeFormatUtil;

import java.time.LocalDate;

/**
 * @Author chendong
 * @create 2019/4/7 18:32
 */
public class CustomLocalDateEditor extends BaseCustomDateTimeEditor<LocalDate> {
    public CustomLocalDateEditor() {
        super(LocalDateTimeFormatUtil::parseLocalDate);
    }
}
