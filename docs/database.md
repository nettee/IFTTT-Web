用户 User （包括会员和管理员）

| 描述 | 字段 | 类型 | 非空 | 备注 |
| --- | --- | --- | --- | --- |
| 用户ID | id | INT | 是 | 主键 |
| 用户名 | name | CHAR(40) | 是 | 不重复 |
| 密码 | password | CHAR(14) | 是 |   |
| 余额 | balance | INT | 是 | 默认值5000 |
| 积分 | score | INT | 是 | 默认值0 |

注：用户等级由积分直接计算得出

消息 Message

| 描述 | 字段 | 类型 | 非空 | 备注 |
| --- | --- | --- | --- | --- |
| 消息ID | id | INT | 是 | 主键 |
| 目标用户ID | userId | INT | 是 |   |
| 发布时间 | publishTime | TIMESTAMP | 是 | 默认值NOW() |
| 摘要 | digest | CHAR(40) | 是 |   |
| 内容 | content | TEXT | 是 |   |
| 是否读过 | opened | BOOL | 是 | 默认值FALSE |

用户任务 UserTask

| 描述 | 字段 | 类型 | 非空 | 备注 |
| --- | --- | --- | --- | --- |
| 用户任务ID | id | INT | 是 | 主键 |
| 用户ID | userId | INT | 是 | 外键 |
| 任务 | task | BLOB | 是 |   |

消费记录 Expense

| 描述 | 字段 | 类型 | 非空 | 备注 |
| --- | --- | --- | --- | --- |
| 消费记录ID | id | INT | 是 | 主键 |
| 用户任务ID | userTaskId | INT | 是 | 外键 |
| 金额 | amount | INT | 是 |   |
