-- camel.camelv_area definition

CREATE TABLE `camelv_area` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `data_id` varchar(64) NOT NULL,
                             `name` varchar(64) DEFAULT NULL COMMENT '工作区名称',
                             `color` varchar(64) DEFAULT NULL COMMENT '工作区颜色',
                             `server_id` varchar(64) DEFAULT NULL COMMENT '关联服务',
                             `left_px` int(64) DEFAULT NULL COMMENT '左侧位置',
                             `top_px` int(64) DEFAULT NULL COMMENT '距离顶部位置',
                             `width` int(64) DEFAULT NULL COMMENT '宽度',
                             `height` int(64) DEFAULT NULL COMMENT '高度',
                             `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
                             `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 正常 1:删除',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- camel.camelv_category definition

CREATE TABLE `camelv_category` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `data_id` varchar(64) NOT NULL,
                                 `name` varchar(64) DEFAULT NULL COMMENT '类别名',
                                 `p_id` varchar(64) DEFAULT NULL COMMENT '父类别id',
                                 `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
                                 `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 正常 1:删除',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- camel.camelv_ftp definition

CREATE TABLE `camelv_ftp` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `data_id` varchar(64) NOT NULL,
                            `name` varchar(64) DEFAULT NULL,
                            `ip` varchar(64) DEFAULT NULL,
                            `port` varchar(64) DEFAULT NULL,
                            `username` varchar(64) DEFAULT NULL,
                            `password` varchar(64) DEFAULT NULL,
                            `type` varchar(64) DEFAULT NULL,
                            `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
                            `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 正常 1:删除',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- camel.camelv_groovy definition

CREATE TABLE `camelv_groovy` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `data_id` varchar(64) NOT NULL,
                               `name` varchar(64) DEFAULT NULL,
                               `script` text,
                               `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
                               `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
                               `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 正常 1:删除',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- camel.camelv_host definition

CREATE TABLE `camelv_host` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `data_id` varchar(64) NOT NULL,
                             `type` varchar(64) DEFAULT NULL,
                             `name` varchar(64) DEFAULT NULL,
                             `ip` varchar(64) DEFAULT NULL,
                             `port` varchar(64) DEFAULT NULL,
                             `username` varchar(64) DEFAULT NULL,
                             `password` varchar(64) DEFAULT NULL,
                             `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
                             `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 正常 1:删除',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- camel.camelv_http definition

CREATE TABLE `camelv_http` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `data_id` varchar(64) NOT NULL,
                             `name` varchar(64) DEFAULT NULL,
                             `url` varchar(64) DEFAULT NULL,
                             `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
                             `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 正常 1:删除',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- camel.camelv_jdbc definition

CREATE TABLE `camelv_jdbc` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `data_id` varchar(64) NOT NULL,
                             `name` varchar(64) DEFAULT NULL,
                             `driver` varchar(64) DEFAULT NULL,
                             `url` varchar(256) DEFAULT NULL,
                             `username` varchar(64) DEFAULT NULL,
                             `password` varchar(64) DEFAULT NULL,
                             `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
                             `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 正常 1:删除',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- camel.camelv_line definition

CREATE TABLE `camelv_line` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `data_id` varchar(64) NOT NULL,
                             `server_id` varchar(64) DEFAULT NULL COMMENT '关联服务',
                             `from_route_id` varchar(64) DEFAULT NULL COMMENT '起始端',
                             `to_route_id` varchar(64) DEFAULT NULL COMMENT '指向端',
                             `type` varchar(64) DEFAULT NULL COMMENT '类型',
                             `m` double DEFAULT NULL COMMENT '线的折点',
                             `name` varchar(64) DEFAULT NULL COMMENT '条件',
                             `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
                             `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 正常 1:删除',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- camel.camelv_mail definition

CREATE TABLE `camelv_mail` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `data_id` varchar(64) NOT NULL,
                             `name` varchar(64) DEFAULT NULL,
                             `mail_host` varchar(64) DEFAULT NULL,
                             `username` varchar(64) DEFAULT NULL,
                             `password` varchar(64) DEFAULT NULL,
                             `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
                             `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 正常 1:删除',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- camel.camelv_node definition

CREATE TABLE `camelv_node` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `data_id` varchar(64) NOT NULL,
                             `ip` varchar(64) DEFAULT NULL,
                             `port` varchar(64) DEFAULT NULL,
                             `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
                             `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 正常 1:删除',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- camel.camelv_queue definition

CREATE TABLE `camelv_queue` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `data_id` varchar(64) NOT NULL,
                              `type` varchar(64) DEFAULT NULL,
                              `name` varchar(64) DEFAULT NULL,
                              `ip` varchar(64) DEFAULT NULL,
                              `port` int(64) DEFAULT NULL,
                              `user_name` varchar(64) DEFAULT NULL,
                              `pass_word` varchar(64) DEFAULT NULL,
                              `queue_name` varchar(64) DEFAULT NULL,
                              `v_host` varchar(64) DEFAULT NULL,
                              `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
                              `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 正常 1:删除',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- camel.camelv_route definition

CREATE TABLE `camelv_route` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `data_id` varchar(64) NOT NULL,
                              `name` varchar(64) DEFAULT NULL COMMENT '路由名称',
                              `type` varchar(64) DEFAULT NULL COMMENT '路由类型',
                              `server_id` varchar(64) DEFAULT NULL COMMENT '关联服务',
                              `related_resource_id` varchar(64) DEFAULT NULL COMMENT '关联的资源id',
                              `param` varchar(64) DEFAULT NULL COMMENT '路由其他参数json格式',
                              `left_px` int(64) DEFAULT NULL COMMENT '左侧位置',
                              `top_px` int(64) DEFAULT NULL COMMENT '距离顶部位置',
                              `width` int(64) DEFAULT NULL COMMENT '宽度',
                              `height` int(64) DEFAULT NULL COMMENT '高度',
                              `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
                              `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 正常 1:删除',
                              `component_option` text,
                              `uri` text,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- camel.camelv_server definition

CREATE TABLE `camelv_server` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `data_id` varchar(64) NOT NULL,
                               `category_id` varchar(64) DEFAULT NULL COMMENT '关联类别id',
                               `name` varchar(64) DEFAULT NULL COMMENT '服务名称',
                               `route_rule` text COMMENT '路由规则',
                               `publish_camel_url` varchar(64) DEFAULT NULL COMMENT '发布节点地址',
                               `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
                               `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 正常 1:删除',
                               `type` varchar(64) DEFAULT NULL,
                               `auth_code` varchar(255) DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- camel.camelv_server_log definition

CREATE TABLE `camelv_server_log` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                   `data_id` varchar(64) NOT NULL,
                                   `name` varchar(64) DEFAULT NULL COMMENT '服务名称',
                                   `status` int(11) DEFAULT NULL COMMENT '服务状态',
                                   `start_date` datetime DEFAULT NULL COMMENT '开始时间',
                                   `end_date` datetime DEFAULT NULL COMMENT '结束时间',
                                   `request_header` text COMMENT '请求头',
                                   `request_body` text COMMENT '请求体',
                                   `response_header` text COMMENT '响应头',
                                   `response_body` text COMMENT '响应体',
                                   `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                   `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 正常 1:删除',
                                   `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;