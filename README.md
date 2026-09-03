# 秒选通 - 后端服务

uniapp 选课系统的后端服务，为前端提供学生选课、教师课程管理、登录认证、AI 问答等接口。

## 技术栈

- Java 8
- Spring Boot 2.7.13
- MyBatis + PageHelper
- MySQL 8（Druid 连接池）
- Redis + Spring Session
- ZooKeeper + Dubbo 3
- Spring Mail
- SpringDoc / OpenAPI 3
- BouncyCastle（SM3 加密）
- Lombok

## 目录结构

```
后端代码/
├── .env.example                     # 环境变量模板
├── .env                             # 本地环境变量（不提交）
├── sql/                              # 数据库初始化脚本
│   └── online-student-choose-system.sql
├── src/main/java/com/jf/
│   ├── UniappCourseBackendApplication.java   # 启动类
│   ├── common/                       # 通用响应封装
│   ├── config/                       # 各类配置
│   ├── controller/                   # 控制器
│   ├── exception/                    # 异常定义
│   ├── mapper/                       # MyBatis Mapper 接口
│   ├── pojo/                         # 实体类
│   ├── service/                      # 业务接口与实现
│   └── utils/                        # 工具类
├── src/main/resources/
│   ├── application.yml               # 主配置
│   ├── application.properties        # 补充配置
│   ├── mapper/                       # MyBatis XML
│   └── logback-spring.xml            # 日志配置
└── pom.xml
```

## 环境要求

- JDK 1.8
- Maven 3.6+
- MySQL 8.x
- Redis 5.x / 6.x
- ZooKeeper 3.7.x

## 快速开始

### 1. 初始化数据库

在 MySQL 中创建数据库，并导入脚本：

```sql
CREATE DATABASE IF NOT EXISTS online_student_choose_system DEFAULT CHARACTER SET utf8mb4;
```

```bash
mysql -u root -p online_student_choose_system < sql/online-student-choose-system.sql
```

### 2. 配置环境变量

项目仿照前端的 `.env` 方式，在根目录提供 `.env.example` 模板。使用时先复制为 `.env`，再填入真实配置：

```bash
cp .env.example .env
```

应用启动时会自动加载根目录下的 `.env` 文件，该文件已被 `.gitignore` 忽略，不会提交到仓库。可配置的变量如下：

| 环境变量 | 说明 | 默认值 |
| --- | --- | --- |
| `DB_URL` | MySQL JDBC 连接串 | `jdbc:mysql://localhost:3306/online_student_choose_system?...` |
| `DB_USERNAME` | MySQL 用户名 | 无，必填 |
| `DB_PASSWORD` | MySQL 密码 | 无，必填 |
| `REDIS_HOST` | Redis 地址 | `localhost` |
| `REDIS_PORT` | Redis 端口 | `6379` |
| `REDIS_PASSWORD` | Redis 密码 | 空 |
| `MAIL_HOST` | SMTP 服务器 | `smtp.qq.com` |
| `MAIL_USERNAME` | 发件邮箱账号 | 无，必填 |
| `MAIL_PASSWORD` | 邮箱授权码 | 无，必填 |
| `DRUID_USERNAME` | Druid 监控页账号 | `admin` |
| `DRUID_PASSWORD` | Druid 监控页密码 | `admin` |
| `ZOOKEEPER_ADDRESS` | ZooKeeper 地址（不含协议头） | `localhost:2181` |

`.env` 文件内容示例：

```dotenv
DB_URL=jdbc:mysql://localhost:3306/online_student_choose_system?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true
DB_USERNAME=uniapp
DB_PASSWORD=your_password
REDIS_HOST=localhost
REDIS_PORT=6379
REDIS_PASSWORD=
MAIL_HOST=smtp.qq.com
MAIL_USERNAME=your_mail@qq.com
MAIL_PASSWORD=your_smtp_auth_code
DRUID_USERNAME=admin
DRUID_PASSWORD=your_druid_password
ZOOKEEPER_ADDRESS=localhost:2181
```

如果运行环境已经设置过同名的系统环境变量，则会优先使用系统环境变量，`.env` 中的同名值不会覆盖。

### 3. 启动 Redis 和 ZooKeeper

确保本机或目标服务器已启动 Redis（默认 6379）和 ZooKeeper（默认 2181）。

### 4. 打包

```bash
mvn clean package
```

打包产物为 `target/uniapp-course-backend.jar`。

### 5. 运行

```bash
java -jar target/uniapp-course-backend.jar
```

服务默认监听 `8080` 端口。

## 接口文档

项目已集成 SpringDoc / OpenAPI，启动后可访问：

- Swagger UI：<http://localhost:8080/swagger-ui.html>
- OpenAPI JSON：<http://localhost:8080/api-docs>

## 主要功能模块

- 登录认证：`LoginController`
- 学生选课：`StudentCourseController`
- 教师课程管理：`TeacherCourseController`
- AI 问答：`AiController`

## 部署说明

项目后端部署在阿里云服务器上，依赖组件为 MySQL、Redis、ZooKeeper，公网 IP：`120.55.112.108`，操作系统为 Ubuntu 24.04 64 位。

## 安全提示

MySQL 密码、邮箱授权码等敏感信息请填写在根目录的 `.env` 文件中，该文件已被 `.gitignore` 忽略，不会提交到仓库。数据库脚本中仍包含初始管理员账号和密码哈希，如仓库对外公开，请先重置相关账号密码。

## 版本控制忽略

项目根目录已提供 `.gitignore`，用于忽略 `target/`、`.idea/`、`*.class`、`*.jar` 等构建和 IDE 产物，以及 `.env` 本地环境变量文件（`.env.example` 会保留）。
