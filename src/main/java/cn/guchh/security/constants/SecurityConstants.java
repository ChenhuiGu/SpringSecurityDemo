package cn.guchh.security.constants;

/**
 * @author chenhuigu
 */
public class SecurityConstants {
    // Swagger WHITELIST
    public static final String[] SWAGGER_WHITELIST = {
            "/swagger-ui.html",
            "/swagger-ui/*",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/webjars/**",
            //knife4j
            "/doc.html",
    };

    // System WHITELIST
    public static final String[] SYSTEM_WHITELIST = {
            "/user/login",
            "/users/sign-up"
    };
}
