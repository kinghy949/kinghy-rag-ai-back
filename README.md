# 使用Spring Ai Alibaba 接入通义大模型

#### 技术栈

- Spring Boot: 3.4.2
- JDK: 17 
- spring-ai: 1.0.0-M5 
- spring-ai-alibaba: 1.0.0-M5.1 
- maven: 3.9.9
- PostgreSQL 16.6
- vector-0.7.2(PostgreSQL的向量存储扩展)
- LLM使用的通义千问
- 对象存储使用阿里云OSS



## 1. 准备工作
### 1.1 准备PostgreSQL数据库并安装好vector-0.7.2扩展后
1. 创建一个数据库，并执行/sql/init.sql

### 1.2 准备阿里云OSS
创建一个OSS Bucket,要求与yml文件中配置的bucket名称相同。

### 1.3 准备通义千问模型
1. 登录通义千问官网，创建模型。
2. 申请apikey并配置到yml文件中。







