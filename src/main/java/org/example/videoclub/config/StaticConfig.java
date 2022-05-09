package org.example.videoclub.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class StaticConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // src/main/resources/static/...
        registry
                .addResourceHandler("/**") // « /css/myStatic.css
//                .addResourceHandler("/static/**") // « /static/css/videoclub.css
                .addResourceLocations("classpath:/static/"); // Default Static Loaction

    }

    @Override
    public void configureViewResolvers(final ViewResolverRegistry registry) {
        registry.jsp("/static/", ".html");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

}
