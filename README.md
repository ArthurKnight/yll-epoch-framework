# yll-epoch-framework
基于springcloud全家桶开发分布式框架
（支持oauth2认证授权、统一下单、公众号服务、Shardingdbc分库分表、常见服务监控、链路监控、异步日志、redis缓存等功能），
实现基于Vue全家桶等前后端分离项目工程。[其它说明](https://github.com/ArthurKnight/yll-epoch-framework/wiki)

# 项目特点
* 框架方面独立、模块相互之间非常独立(feign api 独立工程，方便调用)，第三方工程服务能够轻松接入
* 服务通过网关统一接入，鉴权（网关和业务模块权限独立分开）、监控、日志、缓存等统计分析更加清晰
* 采用分布式模式，部署方便，易于扩展
* 统一下单服务接入（目前只支持微信、后续扩展支付宝等渠道）
* 微信公众号服务接入(服务提供多公众号配置、多子项目调用、公众号相关接口等)
* 采用分布式模式，[docker部署](https://github.com/xxx)方便，易于扩展
* 前端可以基于element ui 、Vue全家桶、React 等主流，实现前后端分离

# 技术模块相关说明
1、 fw-cloud-system 模块
- demo-discovery-eureka  [端口8761] 服务注册与发现(可集群部署)
- demo-provider-user     [端口8000] 服务提供者
- demo-consumer-movie    [端口8010] 服务消费者
- yll-epoch-system-gateway [端口1003] zuul服务网关（外部接口接入入口）
- yll-epoch-system-auth    [端口1004] 权限接入服务(支持oauth2、单点登录) [查看详情 guide](https://github.com/liuweijw/fw-cloud-framework/wiki/05-Auth-%E6%A8%A1%E5%9D%97%E5%90%AF%E5%8A%A8%E8%AF%B4%E6%98%8E)

2、yll-epoch-core 基础公共模块
- cloud-commons 抽离底层基础部分依赖
- yll-epoch-core-beans 常用全局bean
- yll-epoch-core-commons 常量、切面等
- yll-epoch-core-configuration 基础配置
- yll-epoch-core-exception Exception定义
- yll-epoch-core-utils 常用工具类

# 开发部署环境
- MySQL
- Java8
- Lombok
- docker
- git