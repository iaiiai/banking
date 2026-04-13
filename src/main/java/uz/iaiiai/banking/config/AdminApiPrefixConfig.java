package uz.iaiiai.banking.config;

import org.springframework.boot.webmvc.autoconfigure.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

@Configuration
public class AdminApiPrefixConfig implements WebMvcRegistrations {

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping() {
            @Override
            protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
                Class<?> beanType = method.getDeclaringClass();

                if (beanType.getPackageName().contains(".controller.admin")) {
                    RequestMappingInfo adminPrefix =
                            RequestMappingInfo.paths("/admin").build();

                    mapping = adminPrefix.combine(mapping);
                }

                super.registerHandlerMethod(handler, method, mapping);
            }
        };
    }
}