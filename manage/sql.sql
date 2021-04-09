DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `USERNAME` varchar(50) NOT NULL COMMENT '用户名即用户号',
  `GROUP_NAME` int DEFAULT NULL COMMENT '0为管理员1普通用户2快递员3为高管',
  `PASSWORD` varchar(64) NOT NULL COMMENT 'MD5后的密码',
  `GENDER` int NOT NULL DEFAULT '1' COMMENT '性别:1=男性，2=女性',
  `REALNAME` varchar(50) DEFAULT NULL COMMENT '真实姓名可忽略',
  `DEPARTMENT` varchar(50) DEFAULT NULL COMMENT '所在部门可忽略',
  `EMAIL` varchar(64) DEFAULT NULL COMMENT '电子邮件',
  `PHONE_NO` varchar(20) DEFAULT NULL COMMENT '移动电话',
  `AVATAR` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `LAST_PASSWORD_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用户密码上次修改时间',
  `ISDELETE` tinyint NOT NULL DEFAULT '1' COMMENT '删除状态:1=未删除, 2=已删除',
  PRIMARY KEY (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-员工表';

INSERT INTO `user` VALUES ('admin', '0', 'a66abb5684c45962d887564f08346e8d', '1', '超级管理员', null, 'admin@163.com', '13190087809', null, '2021-04-06 21:34:53', '2021-04-06 21:34:53', '2021-04-06 21:34:53', '1');
INSERT INTO `user` VALUES ('zhangsan', '1', '01d7f40760960e7bd9443513f22ab9af', '1', 'zhagnsan', 'string', 'zhangsan@163.com', '18809378912', 'string', '2021-04-06 22:02:22', '2021-04-06 22:02:22', '2021-04-06 22:02:22', '1');




DROP TABLE IF EXISTS `menulist`;
CREATE TABLE `menulist` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `authName` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `description` varchar(255) DEFAULT NULL COMMENT '菜单描述',
  `children` varchar(1000) DEFAULT NULL COMMENT '子菜单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单列表';

INSERT INTO `menulist` VALUES ('1', '员工管理', '员工管理的页面描述，可为null，默认为null', '[{\"authName\":\"添加员工\",\"id\":\"1\",\"path\":\"/string\"},{\"authName\":\"删除员工\",\"id\":\"2\",\"path\":\"/delete\"}]');
INSERT INTO `menulist` VALUES ('2', '员工管理2', null, null);