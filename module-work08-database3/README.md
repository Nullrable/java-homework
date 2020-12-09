
1. `t_order` DDL语句
```
CREATE TABLE `t_order` (
  `order_id` bigint NOT NULL COMMENT '流水号',
  `merchant_id` bigint NOT NULL COMMENT '商户ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL COMMENT '用户名称',
  `money` decimal(16,4) DEFAULT NULL COMMENT '金额',
  `dicount_money` decimal(16,4) DEFAULT NULL COMMENT '优惠金额',
  `deliver_fee` decimal(16,4) DEFAULT NULL COMMENT '运费',
  `create_time` bigint NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单'
```

2. ShardingSphere-jdbc 配置
```
# 配置真实数据源
dataSources:
  # 配置第 1 个数据源
  ds0: !!com.alibaba.druid.pool.DruidDataSource
    driverClassName: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/ds0
    username: root
    password: nhsoft123
  # 配置第 2 个数据源
  ds1: !!com.alibaba.druid.pool.DruidDataSource
    driverClassName: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/ds1
    username: root
    password: nhsoft123

rules:
  # 配置分片规则
  - !SHARDING
    tables:
      # 配置 t_order 表规则
      t_order:
        actualDataNodes: ds${0..1}.t_order${0..15}
        # 配置分库策略
        databaseStrategy:
          standard:
            shardingColumn: user_id
            shardingAlgorithmName: database_inline
        # 配置分表策略
        tableStrategy:
          standard:
            shardingColumn: order_id
            shardingAlgorithmName: table_inline


    # 配置分片算法
    shardingAlgorithms:
      database_inline:
        type: INLINE
        props:
          algorithm-expression: ds${user_id % 2}
      table_inline:
        type: INLINE
        props:
          algorithm-expression: t_order${order_id % 16}
props:
  max-connections-size-per-query: 1
  sql-show: true #显示实际SQL
```

3. 增删改查见 `OrderTests`


4. `Can not update sharding key, logic table: [t_order], column [user_id]` 遇到该错误，表示表分片key支持修改
> JPA下只要调整 @Column(updatable = false), Mybati或者原生SQL只要update set 中不带这个字段就好
