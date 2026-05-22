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

## 项目结构

```
src/main/java/com/example/student/
├── common/                     # 公共模块
│   ├── Result.java             # 统一响应结果
│   ├── PageResult.java         # 分页结果封装
│   ├── BusinessException.java  # 自定义业务异常
│   └── GlobalExceptionHandler.java  # 全局异常处理
├── config/                     # 配置类
│   ├── MyBatisConfig.java      # MyBatis 配置
│   ├── CorsConfig.java         # 跨域配置
│   └── SwaggerConfig.java      # Swagger 文档配置
├── controller/                 # 控制层
│   └── StudentController.java  # 学生管理接口
├── entity/                     # 实体类
│   └── Student.java            # 学生实体
├── mapper/                     # 数据访问层
│   └── StudentMapper.java      # 学生 Mapper
├── service/                    # 业务逻辑层
│   ├── StudentService.java     # 学生服务接口
│   └── impl/
│       └── StudentServiceImpl.java  # 学生服务实现
└── StudentManagementApplication.java  # 启动类
```

## 快速开始

### 1. 环境准备

- JDK 1.8+
- MySQL 8.0+
- Maven 3.6+

### 2. 初始化数据库

执行项目中的 `sql/init.sql` 文件：

```sql
source sql/init.sql;
```

### 3. 配置数据库连接

在 `application.yml` 中配置数据库连接信息，或通过环境变量设置：

```bash
export DB_USERNAME=root
export DB_PASSWORD=your_password
```

### 4. 启动项目

```bash
mvn spring-boot:run
```

启动成功后访问：
- 管理页面：http://localhost:8080/
- API 文档：http://localhost:8080/swagger-ui/

## API 接口

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | `/api/students` | 新增学生 |
| DELETE | `/api/students/{id}` | 删除学生 |
| PUT | `/api/students/{id}` | 修改学生 |
| GET | `/api/students/{id}` | 查询学生详情 |
| GET | `/api/students?name=&page=1&pageSize=10` | 分页查询学生列表 |

### 统一响应格式

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {}
}
```

### 分页响应格式

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "list": [],
    "total": 0,
    "page": 1,
    "pageSize": 10
  }
}
```

### 错误响应

| 状态码 | 说明 |
|--------|------|
| 200 | 操作成功 |
| 400 | 参数校验失败 / 业务异常 |
| 500 | 服务器内部错误 |
