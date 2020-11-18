以下代码测试都写在 ModuleWork05Application

### 01 work01 写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以)

>主要是 xml，注解(ComponentScan 与 @Service， @Controller, @Component， @Configuration),
EnableAutoConfiguration

### 02 work02 给前面课程提供的 Student/Klass/School 实现自动配置和 Starter
>代码主要见 module-work05-spring-starter里 HelloServiceAutoConfiguration 和 JdbcAutoConfiguration
JdbcAutoConfiguration 用于后面JDBC实验

### 03 work03  
**01 使用 JDBC 原生接口，实现数据库的增删改查操作。**
>详见 JdbcService 和 JdbcAutoConfiguration (自定义数据库池)


**02 使用事务，PrepareStatement 方式，批处理方式，改进上述操作。**
>jdbcService.testTx();

**03 配置 Hikari 连接池，改进上述操作。**
>hikariService.save(); 