package cn.guchh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenhuigu
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                // 设置扫描路径
                .apis(RequestHandlerSelectors.basePackage("cn.guchh.controller"))
                .build();
                //.globalRequestParameters(getGlobalRequestParameters())
                //.globalResponses(HttpMethod.GET, getGlobalResponseMessage())
                //.globalResponses(HttpMethod.POST, getGlobalResponseMessage());
    }
    /**
     * 生成接口信息，包括标题、联系人等
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringSecurity接口文档")
                .description("如有疑问，可联系烂笔头，微信：lanbtou")
                .contact(new Contact("烂笔头", "https://www.choupangxia.com/", "secbro2@gmail.com"))
                .version("1.0")
                .build();
    }

    /**
     * 封装全局通用参数
     */
    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("uuid")
                .description("设备uuid")
                .required(true)
                .in(ParameterType.QUERY)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build());
        return parameters;
    }

    /**
     * 封装通用响应信息
     */
    private List<Response> getGlobalResponseMessage() {
        List<Response> responseList = new ArrayList<>();
        responseList.add(new ResponseBuilder().code("404").description("未找到资源").build());
        return responseList;
    }

}
