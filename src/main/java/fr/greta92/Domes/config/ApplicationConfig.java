    package fr.greta92.Domes.config;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.ComponentScan;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
    import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
    import org.springframework.web.servlet.view.InternalResourceViewResolver;
    import org.springframework.web.servlet.view.JstlView;
    @Configuration
    @ComponentScan(basePackages = "fr.greta92.Domes")
    public class ApplicationConfig extends WebMvcConfigurationSupport {
        @Override
        protected void addResourceHandlers(ResourceHandlerRegistry registry) {
            //On dit a spring ou se situe les css et les images
            registry.addResourceHandler("css/**",
                    "images/**").addResourceLocations("classpath:/static/css/", "classpath:/static/images/");
        }
        @Bean
        public InternalResourceViewResolver jspViewResolver() {
            InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
            viewResolver.setPrefix("/WEB-INF/jsp/");
            viewResolver.setSuffix(".jsp");
            viewResolver.setViewClass(JstlView.class);
            return viewResolver;
        }
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }


    }

