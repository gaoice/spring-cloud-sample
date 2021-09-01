# spring-cloud-sample

## 说明

spring cloud 模板项目，此项目实现了授权服务和网关鉴权，使用的是较为常用的实现方式。
现在 Spring 对于授权服务有新的实现方式，参见 https://github.com/spring-projects/spring-authorization-server 。

## 模块
nebula-auth：授权服务

nebula-common：公共模块

nebula-gateway：带 token 验证和鉴权功能的网关，统一鉴权

nebula-modules：封装的模块

nebula-services：微服务

