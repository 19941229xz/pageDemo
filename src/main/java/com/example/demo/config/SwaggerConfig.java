package com.example.demo.config;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
/**
 * Created by xiongzh on 2018/3/21.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
 
    @Bean
    public Docket docket(){
    	ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();  
    	ticketPar.name("Authorization").description("用户token")
    	.modelRef(new ModelRef("string")).parameterType("header") 
    	.required(false).build(); //header中的ticket参数非必填，传空也可以
    	pars.add(ticketPar.build()); 
    	
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
//                   当前包路径
                   .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                    .paths(PathSelectors.any()).build().globalOperationParameters(pars);
        
//        return new Docket(DocumentationType.SWAGGER_2)
//        		.select()
//        		.apis(RequestHandlerSelectors.any())  
//                .build()  
//                .globalOperationParameters(pars)  
//                .apiInfo(apiInfo());
 
    }
//构建api文档的详细信息函数
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                //页面标题
                    .title("springBoot测试使用Swagger2构建RESTful API")
                //创建人
                    .contact(new Contact("xiongzh","http://www.baidu.com",""))
                 //版本号
                    .version("1.0")
                //描述
                    .description("API 描述")
                    .termsOfServiceUrl("xiongzh")
                    .build();
    }
}
