package com.hcyzzl.mks.ds.user.dao;

import com.hcyzzl.mks.ds.common.dao.BaseDao;
import com.hcyzzl.mks.ds.user.entity.UserInfo;
import com.hcyzzl.mks.ds.user.vo.UserInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author chendong
 * @create 2019/4/12 0:22
 */
public interface UserInfoMapper extends BaseDao<UserInfo> {

    /**
     * @param userInfoVo
     * @return
     */
    List<UserInfoVo> listByParam(@Param("userInfoVo") UserInfoVo userInfoVo);
}
