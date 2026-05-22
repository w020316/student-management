# 学生信息管理系统

基于 Spring Boot + MyBatis 的学生信息管理后端服务，遵循 RESTful API 设计规范。

## 技术栈

| 技术 | 版本 | 说明 |
|------|------|------|
| Spring Boot | 2.7.18 | 基础框架 |
| MyBatis | 2.3.2 | ORM 框架 |
| MySQL | 8.0 | 数据库 |
| Lombok | - | 简化实体类 |
| Validation | - | 参数校验 |
| Swagger | 3.0.0 | API 文档 |
| Docker | - | 容器化部署 |

## 项目结构

```
src/main/java/com/example/student/
├── common/                     # 公共模块
│   ├── Result.java             # 统一响应结果
│   ├── PageResult.java         # 分页结果封装
│   ├── DashboardStats.java     # 仪表盘统计数据
│   ├── BusinessException.java  # 自定义业务异常
│   └── GlobalExceptionHandler.java  # 全局异常处理
├── config/                     # 配置类
│   ├── MyBatisConfig.java      # MyBatis 配置
│   ├── CorsConfig.java         # 跨域配置
│   └── SwaggerConfig.java      # Swagger 文档配置
├── controller/                 # 控制层
│   ├── StudentController.java  # 学生管理接口
│   └── SystemController.java   # 系统监控接口
├── entity/                     # 实体类
│   └── Student.java            # 学生实体
├── mapper/                     # 数据访问层
│   ├── StudentMapper.java      # 学生 Mapper
│   └── StatsMapper.java        # 统计 Mapper
├── service/                    # 业务逻辑层
│   ├── StudentService.java     # 学生服务接口
│   └── impl/
│       └── StudentServiceImpl.java  # 学生服务实现
└── StudentManagementApplication.java  # 启动类
```

## 快速开始

### 方式一：本地运行

#### 1. 环境准备

- JDK 1.8+
- MySQL 8.0+
- Maven 3.6+

#### 2. 初始化数据库

```sql
source sql/init.sql;
source sql/seed.sql;   -- 可选：插入演示数据
```

#### 3. 配置数据库连接

通过环境变量设置（推荐）：

```bash
export DB_USERNAME=root
export DB_PASSWORD=your_password
```

或直接修改 `application-dev.yml`。

#### 4. 启动项目

```bash
mvn spring-boot:run
```

### 方式二：Docker 部署

```bash
docker-compose up -d
```

这将自动启动 MySQL + 应用，并初始化数据库和演示数据。

启动成功后访问：
- 管理页面：http://localhost:8080/
- API 文档：http://localhost:8080/swagger-ui/
- GitHub Pages：https://w020316.github.io/student-management/

## API 接口

### 学生管理

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | `/api/students` | 新增学生 |
| DELETE | `/api/students/{id}` | 删除学生 |
| PUT | `/api/students/{id}` | 修改学生 |
| GET | `/api/students/{id}` | 查询学生详情 |
| GET | `/api/students?name=&page=1&pageSize=10` | 分页查询学生列表 |

### 系统监控

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/stats` | 仪表盘统计数据 |
| GET | `/api/health` | 健康检查 |

### 统一响应格式

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {}
}
```

### 仪表盘统计响应

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "totalStudents": 10,
    "averageAge": 20.7,
    "minorCount": 1,
    "adultCount": 9
  }
}
```

### 错误响应

| 状态码 | 说明 |
|--------|------|
| 200 | 操作成功 |
| 400 | 参数校验失败 / 业务异常 |
| 500 | 服务器内部错误 |

## 多环境配置

| 环境 | 配置文件 | 说明 |
|------|----------|------|
| 开发 | `application-dev.yml` | 本地开发，开启 SQL 日志 |
| 生产 | `application-prod.yml` | Docker 部署，关闭 SQL 日志 |

切换环境：

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
