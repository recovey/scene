# ETL场景1

是英文Extract-Transform-Load的缩写，用来描述将数据从来源端经过抽取（extract）、转换（transform）、加载（load）至目的端的过程。ETL一词较常用在数据仓库

## 需求：

在某一个行业领域中，上游会通过某组件（Kafka）把数据同步传输给下游接口，数据主要有2种：

- 客户数据，应该被存储到`Customer`表中

  - 报文格式

    ```json
    { id:1, customer_name:"xxx",....}
    ```

    

- 寄件数据，应该被存储到`Send`表中

  - 报文格式

    ```json
    {id:1,send_no:'001',...,customer_id:1}
    ```

    

但是为了平时运营的查询响应速度不至于太慢，因此，在寄件表中，对客户信息的字段做了适当冗余

| 字段名称      | 说明                                                       |      |
| ------------- | ---------------------------------------------------------- | ---- |
| send_id       | 主键                                                       |      |
| send_no       | 业务编号，唯一                                             |      |
| ...           |                                                            |      |
| customer_id   | 关联客户的主键                                             |      |
| customer_name | 客户的名称，该字段冗余了，因为在customer中，是由这个字段的 |      |

```sql
select s.*,c.customer_name from send s
	left join customer c on s.customer_id = c.id limit...
	
select * from send s limit...
```





为了满足在网点使用该系统，需要通过一个接口来对send表的customer_name进行填充

```sql
# 核心SQL
update send 
	set customer_name = (select customer_name from customer where id = a) 
	where customer_id = a
#在接收到send的报文的时候，先根据报文中的customer_id发出查询语句
select customer_name from customer where id = ?

insert into send values(...,#{customerName}...);
```

## 场景模拟

自建一张mq_send，在其中准备200w-300w的数据，然后使用分页查询的技术，把数据一页一页的查询到Java程序中，再准备约10w的customer的静态数据

