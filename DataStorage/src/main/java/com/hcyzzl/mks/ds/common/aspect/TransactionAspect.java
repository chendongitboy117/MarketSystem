package com.hcyzzl.mks.ds.common.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Properties;

/**
 * 事务配置
 * @Author chendong
 * @create 2019/4/7 16:39
 */
public class TransactionAspect {

    private final Logger log = LoggerFactory.getLogger(getClass());
    /**
     * 自定义一个事务的拦截器 Bean
     *
     * @return TransactionInterceptor
     */
    @Bean
    public TransactionInterceptor txAdvice(DataSourceTransactionManager transactionManager) {
        if (transactionManager == null) {
            log.error("transaction manager is null, com.zx.idc.ds.common.aspect.TransactionAspect configuration failed!");
            return null;
        }
        Properties properties = new Properties();
        String propagationRequiredExceptionReadOnly = "PROPAGATION_REQUIRED,-Exception,readOnly";
        String propagationRequiredException = "PROPAGATION_REQUIRED,-Exception";
        String[] queryKeys = {"list*", "find*", "count*", "get*", "select*", "load*", "look*", "search*", "datagrid*", "query*", "check"};
        String[] updateKeys = {"insert*", "add*", "new*", "create*", "save*", "cancel*", "truncate*", "del*", "delete*", "remove*", "update*", "modify*", "set*"};
        String[] defaultKeys = {"*"};
        setBatchProperties(properties, propagationRequiredExceptionReadOnly, queryKeys);
        setBatchProperties(properties, propagationRequiredException, updateKeys);
        setBatchProperties(properties, propagationRequiredExceptionReadOnly, defaultKeys);
        return new TransactionInterceptor(transactionManager, properties);
    }

    /**
     * 设置 properties 的属性
     *
     * @param value 事务级别
     * @param keys  方法名匹配列表
     */
    private void setBatchProperties(Properties properties, String value, String... keys) {
        for (String key : keys) {
            properties.setProperty(key, value);
        }
    }

    /**
     * 根据 Bean 的名称去拦截并添加事务
     *
     * @return 通过 Bean 名称列表匹配并标识要代理的类, 自动创建代理
     */
    @Bean
    public BeanNameAutoProxyCreator defaultPointcutAdvisor() {
        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        creator.setInterceptorNames("txAdvice");
        creator.setBeanNames("*Service");
        creator.setProxyTargetClass(true);
        return creator;
    }
}
