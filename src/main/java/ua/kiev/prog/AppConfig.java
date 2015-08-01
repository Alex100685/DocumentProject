package ua.kiev.prog;

import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan("ua.kiev.prog")
@EnableWebMvc
//@EnableScheduling
@ImportResource("WEB-INF/spring-security.xml")
@EnableAspectJAutoProxy
public class AppConfig {
	
	@Bean
	public Actions actions() {
	        return new ActionsImpl();
	    }
    @Bean
    public EntityManager entityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpringMVC6");
        return emf.createEntityManager();
    }
    
    @Bean
    public EntityManager entityManager2() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpringMVC6");
        return emf.createEntityManager();
    }
    
    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setOrder(1);
        return resolver;
    }
    
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }
    
    @Bean
    public AutoconnectionAspect autoconnectionAspect() {
        return new AutoconnectionAspect();
    }

    
}
