package com.hcyzzl.mks.common.ref.copy;

import com.hcyzzl.mks.common.lang.ListUtil;
import org.joor.Reflect;
import org.joor.ReflectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;



/**
 * 真正执行 copy 属性的类
 * @Author chendong
 * @create 2019/4/7 17:36
 */
public class BeanCopyOperator<F, T> {

    private static final Logger log = LoggerFactory.getLogger(BeanCopyUtil.class);
    private final F from;
    private final T to;
    private final BeanCopyConfig config;
    private List<BeanCopyField> copyFieldList;

    public BeanCopyOperator(F from, T to, List<BeanCopyField> copyFieldList, BeanCopyConfig config) {
        this.from = from;
        this.to = to;
        this.copyFieldList = copyFieldList;
        this.config = config;
    }

    public void copy() {
        //获取到两个对象所有的属性
        final Map<String, Reflect> fromFields = Reflect.on(from).fields();
        final Reflect to = Reflect.on(this.to);
        final Map<String, Reflect> toFields = to.fields();
        //过滤出所有相同字段名的字段并进行拷贝
        if (config.isSame()) {
            final Map<ListUtil.ListDiffState, List<String>> different = ListUtil.different(new ArrayList<>(fromFields.keySet()), new ArrayList<>(toFields.keySet()));
            copyFieldList = Stream.concat(different.get(ListUtil.ListDiffState.common).stream()
                    .map(s -> new BeanCopyField(s, s, null)), copyFieldList.stream())
                    .collect(Collectors.toList());
        }
        //根据拷贝字段列表进行拷贝
        copyFieldList.stream()
                //忽略空值
                .filter(beanCopyField -> !config.isIgnoreNull() || fromFields.get(beanCopyField.getFrom()).get() != null)
                //覆盖属性
                .filter(beanCopyField -> config.isOverride() || toFields.get(beanCopyField.getTo()).get() == null)
                //如果没有转换器，则使用默认的转换器
                .peek(beanCopyField -> {
                    if (beanCopyField.getConverter() == null) {
                        beanCopyField.setConverter(Function.identity());
                    }
                })
                .forEach(beanCopyField -> {
                    final String fromField = beanCopyField.getFrom();
                    final F from = fromFields.get(fromField).get();
                    final String toField = beanCopyField.getTo();
                    try {
                        to.set(toField, beanCopyField.getConverter().apply(from));
                    } catch (ReflectException e) {
                        log.warn("Copy field failed, from {} to {}, exception is {}", fromField, toField, e.getMessage());
                    }
                });
    }
}
