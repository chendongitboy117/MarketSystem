package com.hcyzzl.mks.ds.common.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.hcyzzl.mks.ds.common.dao.BaseDao;
import com.hcyzzl.mks.ds.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

/**
 * 业务层基础接口
 * @Author chendong
 * @create 2019/4/7 16:34
 */
public interface BaseService<T extends BaseEntity> extends IService<T> {
    /**
     * 根据 id 查询数据是否存在
     *
     * @param id 数据 id
     * @return 数据是否存在
     */
    Boolean existsById(Serializable id);

    /**
     * 根据对象查询数据是否存在
     *
     * @param t 实体类
     * @return 是否存在满足条件的数据
     */
    default boolean exists(T t) {
        return this.selectCount(new EntityWrapper<>(t)) > 0;
    }

    /**
     * 获取指定数量的随机数据
     *
     * @param size 随机数据的条数
     * @return 随机数据
     */
    List<T> listByRandom(Integer size);

    /**
     * 获取全部数据
     *
     * @return 全部数据
     */
    List<T> listForAll();

    /**
     * 自动将实体类参数对象包装为 {@link EntityWrapper<T>} 的 {@link #selectList(Wrapper)} 查询
     *
     * @param t 实体类
     * @return 查询到的数据列表
     */
    default List<T> selectList(T t) {
        return selectList(new EntityWrapper<>(t));
    }

    /**
     * 自动将实体类参数对象包装为 {@link EntityWrapper<T>} 的 {@link #selectOne(Wrapper)} 查询
     *
     * @param t 实体类
     * @return 查询到的数据
     */
    default T selectOne(T t) {
        return (T) selectOne(new EntityWrapper<>(t));
    }

    /**
     * 查询第一个符合条件的对象
     *
     * @param t 实体类
     * @return 查询到的数据
     */
    default T selectFirst(T t) {
        final List<T> list = selectList(new EntityWrapper<>(t));
        return list.isEmpty() ? null : list.get(0);
    }



    /**
     * 自动将实体类参数对象包装为 {@link EntityWrapper<T>} 的 {@link #selectPage(Page, Wrapper)} 查询
     * @param page
     * @param t 实体类
     * @return 查询到的分页数据列表
     */
    default Page<T> selectPage(Page<T> page, T t) {
        return selectPage(page, new EntityWrapper<>(t));
    }

    /**
     * 自动将实体类参数对象包装为 {@link EntityWrapper<T>} 的 {@link #delete(Wrapper)} 查询
     *
     * @param t 实体类
     * @return 删除结果
     */
    default boolean delete(T t) {
        return delete(new EntityWrapper<>(t));
    }

    /**
     * 根据实体类列表批量查询实体类列表
     *
     * @param list 实体类列表
     * @return 查询到的实体类列表（自动去重）
     */
    List<T> listBatchByEntityList(List<T> list);

    /**
     * 手动分页查询
     *
     * @param page     分页信息
     * @param entity   查询实体参数，可以是继承实体类的 VO 类对象
     * @param fn       查询方法
     * @param <Entity> 实体类型，或是其子类
     * @return 查询到的分页信息
     */
    <Entity extends T> Page<Entity> manualPagination(Page<Entity> page, Entity entity, Function<Entity, List<Entity>> fn);

}
