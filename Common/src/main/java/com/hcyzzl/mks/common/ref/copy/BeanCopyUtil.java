package com.hcyzzl.mks.common.ref.copy;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * 拷贝属性的配置
 * @Author chendong
 * @create 2019/4/7 17:30
 */
public class BeanCopyUtil<F, T>  {
        /**
         * 源对象
         */
        private final F from;
        /**
         * 目标对象
         */
        private final T to;
        /**
         * 拷贝的字段信息列表
         */
        private final List<BeanCopyField> copyFieldList = new LinkedList<>();
        /**
         * 配置信息
         */
        private BeanCopyConfig config = new BeanCopyConfig();

        private BeanCopyUtil(F from, T to) {
            this.from = from;
            this.to = to;
        }

        /**
         * 指定需要拷贝的源对象和目标对象
         *
         * @param from 源对象
         * @param to   目标对象
         * @param <F>  源对象类型
         * @param <T>  目标对象类型
         * @return 一个 {@link BeanCopyUtil} 对象
         */
        public static <F, T> BeanCopyUtil<F, T> copy(F from, T to) {
            return new BeanCopyUtil<>(from, to);
        }

        /**
         * 拷贝指定对象的字段
         *
         * @param fromField 源对象中的字段名
         * @param toField   目标对象中的字段名
         * @param converter 将源对象中字段转换为目标对象字段类型的转换器
         * @return 返回 {@code this}
         */
        public BeanCopyUtil<F, T> prop(String fromField, String toField, Function<? super Object, ? super Object> converter) {
            copyFieldList.add(new BeanCopyField(fromField, toField, converter));
            return this;
        }

        /**
         * 拷贝指定对象的字段
         *
         * @param fromField 源对象中的字段名
         * @param toField   目标对象中的字段名
         * @return 返回 {@code this}
         */
        public BeanCopyUtil<F, T> prop(String fromField, String toField) {
            return prop(fromField, toField, null);
        }

        /**
         * 拷贝指定对象的字段
         *
         * @param field     源对象中与目标对象中的字段名
         * @param converter 将源对象中字段转换为目标对象字段类型的转换器
         * @return 返回 {@code this}
         */
        public BeanCopyUtil<F, T> prop(String field, Function<? super Object, ? super Object> converter) {
            return prop(field, field, converter);
        }

        /**
         * 拷贝指定对象的字段
         *
         * @param field 源对象中与目标对象中的字段名
         * @return 返回 {@code this}
         */
        public BeanCopyUtil<F, T> prop(String field) {
            return prop(field, field, null);
        }

        /**
         * 拷贝指定对象的多个字段
         *
         * @param fields 源对象中与目标对象中的多个字段名
         * @return 返回 {@code this}
         */
        public BeanCopyUtil<F, T> props(String... fields) {
            for (String field : fields) {
                prop(field);
            }
            return this;
        }

        /**
         * 执行真正的拷贝操作
         *
         * @return 返回 {@code this}
         */
        public BeanCopyUtil<F, T> exec() {
            new BeanCopyOperator<>(from, to, copyFieldList, config).copy();
            return this;
        }

        /**
         * 重新开始添加其他对象的属性
         * 用于在执行完 {@link #exec()} 之后还想复制其它对象的属性
         *
         * @param from 源对象
         * @param <R>  源对象类型
         * @return 一个新的 {@link BeanCopyUtil} 对象
         */
        public <R> BeanCopyUtil<R, T> from(R from) {
            return new BeanCopyUtil<>(from, to);
        }

        /**
         * 返回当前的目标对象
         *
         * @return 当前的目标对象
         */
        public T get() {
            return to;
        }

        /**
         * 配置拷贝的一些策略
         *
         * @param config 拷贝配置对象
         * @return 返回 {@code this}
         */
        public BeanCopyUtil<F, T> config(BeanCopyConfig config) {
            this.config = config;
            return this;
        }

}
