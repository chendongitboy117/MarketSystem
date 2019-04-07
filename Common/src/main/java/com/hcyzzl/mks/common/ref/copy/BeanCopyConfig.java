package com.hcyzzl.mks.common.ref.copy;

/**
 * @Author chendong
 * @create 2019/4/7 17:34
 */
public class BeanCopyConfig {
    /**
     * 同名的字段自动复制
     */
    private boolean same = true;
    /**
     * 覆盖同名的字段
     */
    private boolean override = true;
    /**
     * 忽略 {@code null} 的源对象属性
     */
    private boolean ignoreNull = true;

    public BeanCopyConfig() {
    }

    public BeanCopyConfig(boolean same, boolean override, boolean ignoreNull) {
        this.same = same;
        this.override = override;
        this.ignoreNull = ignoreNull;
    }

    public boolean isSame() {
        return same;
    }

    public BeanCopyConfig setSame(boolean same) {
        this.same = same;
        return this;
    }

    public boolean isOverride() {
        return override;
    }

    public BeanCopyConfig setOverride(boolean override) {
        this.override = override;
        return this;
    }

    public boolean isIgnoreNull() {
        return ignoreNull;
    }

    public BeanCopyConfig setIgnoreNull(boolean ignoreNull) {
        this.ignoreNull = ignoreNull;
        return this;
    }
}
