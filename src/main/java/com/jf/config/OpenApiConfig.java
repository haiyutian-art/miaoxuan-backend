package com.jf.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringDoc OpenAPI 配置类
 * 替代原来的 Springfox Swagger 配置
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI uniappCourseOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("UniApp 选课系统 API")
                        .description("基于 Spring Boot 的选课系统后端 API 文档")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("UniApp Course System")
                                .email("admin@example.com")
                                .url("https://github.com/yourusername/uniapp-course-backend"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
} 