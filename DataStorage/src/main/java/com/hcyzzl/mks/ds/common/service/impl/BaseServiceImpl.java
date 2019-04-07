package com.hcyzzl.mks.ds.common.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hcyzzl.mks.common.ref.copy.BeanCopyUtil;
import com.hcyzzl.mks.ds.common.dao.BaseDao;
import com.hcyzzl.mks.ds.common.entity.BaseEntity;
import com.hcyzzl.mks.ds.common.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author chendong
 * @create 2019/4/7 17:22
 */
public abstract class  BaseServiceImpl<M extends BaseDao<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements BaseService<T> {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 根据 id 查询数据是否存在
     *
     * @param id 数据 id
     * @return 数据是否存在
     */
    @Override
    public Boolean existsById(Serializable id) {
        return baseMapper.existsById(id);
    }

    @Override
    public List<T> listByRandom(Integer size) {
        return baseMapper.listByRandom(size);
    }

    @Override
    public List<T> listForAll() {
        return baseMapper.listForAll();
    }

    @Override
    public List<T> listBatchByEntityList(List<T> entityList) {
         return entityList.stream()
                .flatMap(t -> selectList(t).stream())
                .distinct()
                .collect(Collectors.toList());
    }
    @Override
    public <Entity extends T> Page<Entity> manualPagination(Page<Entity> page, Entity entity, Function<Entity, List<Entity>> fn) {
        PageHelper.setPagination(page);
        final List<Entity> list = fn.apply(entity);
        page.setRecords(list);
        BeanCopyUtil.copy(PageHelper.getPagination(), page).exec();
        PageHelper.freeTotal();
        return page;
    }

    @Override
    public List<T> selectBatchIds(Collection<? extends Serializable> idList) {
        return idList.isEmpty() ? Collections.emptyList() : super.selectBatchIds(idList);
    }

    @Override
    public boolean insertBatch(List<T> entityList) {
        return entityList.isEmpty() || super.insertBatch(entityList);
    }

    @Override
    public boolean updateBatchById(List<T> entityList) {
        return entityList.isEmpty() || super.updateBatchById(entityList);
    }

    @Override
    public boolean deleteBatchIds(Collection<? extends Serializable> idList) {
        return idList.isEmpty() || super.deleteBatchIds(idList);
    }

    @Override
    public boolean insertOrUpdateBatch(List<T> entityList) {
        return entityList.isEmpty() || super.insertOrUpdateBatch(entityList);
    }

}
