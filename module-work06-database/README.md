```
/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   user_id              bigint not null comment '用户',
   merchant_id          varchar(32) not null comment '商户id',
   user_nick_name       varchar(50) not null comment '昵称',
   user_phone           varchar(32) not null comment '手机号码',
   create_time          bigint not null comment '创建时间戳',
   update_time          bigint not null comment '修改时间戳',
   primary key (merchant_id, user_id)
)COMMENT='用户'
ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4;


﻿
/*==============================================================*/
/* Table: user_address                                          */
/*==============================================================*/
create table user_address
(
   address_id           bigint not null comment '地址ID',
   merchant_id          varchar(32) not null comment '商户ID',
   country              varchar(50) not null comment '国家',
   province             varchar(50) not null comment '省份',
   city                 varchar(50) not null comment '城市',
   street               varchar(50) not null comment '街道',
   address              varchar(250) not null comment '详细地址',
   receiver             varchar(50) not null comment '收货人',
   receiver_phone       varchar(20) not null comment '收货人手机号',
   primary key (merchant_id, address_id)
)COMMENT='用户地址'
ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4;


﻿/*==============================================================*/
/* Table: goods                                                 */
/*==============================================================*/
create table goods
(
   goods_id             bigint not null comment '商品ID',
   merchant_id          varchar(32) not null comment '商户ID',
   name                 varchar(250) not null comment '名称',
   spec                 varchar(50) not null comment '规格',
   unit                 varchar(20) not null comment '单位',
   std_price            decimal(16,8) not null comment '标准售价',
   member_price         decimal(16,8) comment '会员价',
   desc_url             varchar(250) not null comment '详情外部地址',
   create_time          bigint not null comment '创建时间',
   update_time          bigint not null comment '更新时间',
   primary key (merchant_id, goods_id)
)COMMENT='商品'
ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4;

﻿/*==============================================================*/
/* Table: goods_media                                           */
/*==============================================================*/
create table goods_media
(
   media_id             bigint not null comment '多媒体ID',
   goods_id             bigint not null comment '商品ID',
   merchant_id          varchar(32) not null comment '商户ID',
   media_url            varchar(250) not null comment '多媒体URL',
   media_type           varchar(50) not null comment '多媒体类型',
   create_time          bigint not null comment '创建时间',
   update_time          bigint not null comment '更新时间',
   primary key (goods_id, media_id)
)COMMENT='商品多媒体文件'
ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4;

﻿/*==============================================================*/
/* Table: "order"                                               */
/*==============================================================*/
create table "order"
(
   order_id             bigint not null comment '流水号',
   merchant_id          varchar(32) not null comment '商户ID',
   user_id              bigint not null comment '用户ID',
   user_name            varchar(50) not null comment '用户名称',
   money                decimal(16,4) comment '金额',
   deliver_fee          decimal(16,4) comment '运费',
   create_time          bigint not null comment '创建时间',
   primary key (order_id)
)COMMENT='订单'
ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4;


﻿/*==============================================================*/
/* Table: order_address                                         */
/*==============================================================*/
create table order_address
(
   order_id             bigint not null comment '单据流水号',
   merchant_id          varchar(32) not null comment '商户ID',
   country              varchar(50) not null comment '国家',
   province             varchar(50) not null comment '省份',
   city                 varchar(50) not null comment '城市',
   street               varchar(50) not null comment '街道',
   address              varchar(250) not null comment '详细地址',
   receiver             varchar(50) not null comment '收货人',
   receiver_phone       varchar(20) not null comment '收货人地址',
   memo                 varchar(250) comment '备注',
   primary key (order_id)
)COMMENT='订单地址'
ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4;


﻿/*==============================================================*/
/* Table: order_goods                                            */
/*==============================================================*/
create table order_goods
(
   order_id             bigint not null comment '单据流水号',
   order_goods_num      int not null comment '单据明细编号',
   merchant_id          varchar(32) not null comment '商户ID',
   goods_id             bigint not null comment '商品ID',
   name                 varchar(50) not null comment '名称',
   spec                 varchar(50) not null comment '规格',
   unit                 varchar(20) not null comment '单位',
   std_price            decimal(16,8) comment '标准售价',
   member_price         decimal(16,8) comment '会员价',
   desc_url             varchar(250) not null comment '详情URL',
   media_id             bigint not null comment '媒体ID',
   media_url            varchar(250) not null comment '媒体地址',
   media_type           varchar(50) not null comment '媒体类型',
   market_activity_id   bigint comment '营销活动ID',
   discount_scheme_id   bigint comment '折扣方案ID',
   sale_price           decimal(16,8) not null comment '实际售价',
   sale_qty             decimal(16,4) not null comment '销售数量',
   sale_money           decimal(16,4) not null comment '销售金额',
   sale_dicount_money   decimal(16,4) not null comment '折扣金额',
   create_time          bigint not null comment '创建时间戳',
   primary key (order_id, order_goods_num)
)COMMENT='订单商品'
ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4;


﻿/*==============================================================*/
/* Table: order_state                                           */
/*==============================================================*/
create table order_state
(
   order_id             bigint not null comment '单据流水号',
   state_no             int not null comment '状态序号',
   state_code           int not null comment '状态编号',
   state_name           varchar(200) not null comment '状态名称',
   operate_time         bigint not null comment '操作时间戳',
   operator             varchar(50) not null comment '操作人',
   primary key (order_id, state_no)
)COMMENT='订单状态'
NGINE = InnoDB
DEFAULT CHARSET = utf8mb4;
```