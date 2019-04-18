package com.hcyzzl.mks.ds.user.entity;

import com.hcyzzl.mks.ds.common.entity.BaseEntity;

/**
 * @Author chendong
 * @create 2019/4/12 0:21
 */

public class UserInfo extends BaseEntity {
  /*`u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_username` varchar(255) DEFAULT NULL,
  `u_phone` varchar(255) NOT NULL,
  `u_password` varchar(32) NOT NULL,
  `u_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `u_credit` float DEFAULT '80',
            `u_type` int(11) DEFAULT NULL,
  `u_type_id` int(11) DEFAULT NULL,
  `u_balance` double DEFAULT NULL,
            `u_sale` double DEFAULT NULL,*/
  private Integer id;
  private String userName;
  private String phone;


}
