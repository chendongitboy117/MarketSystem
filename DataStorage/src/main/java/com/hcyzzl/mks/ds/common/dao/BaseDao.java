package com.hcyzzl.mks.ds.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hcyzzl.mks.ds.common.entity.BaseEntity;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * 基础 Mapper，所以子类都要继承该基类
 * @Author chendong
 * @create 2019/4/7 16:36
 */
public interface BaseDao<T extends BaseEntity> extends BaseMapper<T> {
    /**
     * 根据 id 查询数据是否存在
     *
     * @param id 数据 id
     * @return 数据是否存在
     */
    Boolean existsById(@Param("id") Serializable id);

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

}
