package com.hcyzzl.mks.backend.common.parse;

import com.hcyzzl.mks.common.utils.LocalDateTimeFormatUtil;

import java.time.LocalTime;

/**
 * @Author chendong
 * @create 2019/4/7 18:33
 */
public class CustomLocalTimeEditor extends BaseCustomDateTimeEditor<LocalTime> {
    public CustomLocalTimeEditor() {
        super(LocalDateTimeFormatUtil::parseLocalTime);
    }

}
