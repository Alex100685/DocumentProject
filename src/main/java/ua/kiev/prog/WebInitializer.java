package ua.kiev.prog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;









import ua.kiev.prog.security.AuthenticationListener;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        
        ctx.setServletContext(servletContext);

        servletContext.addListener(new ContextLoaderListener(ctx));
        
        ctx.addApplicationListener(new AuthenticationListener());
        

      ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
      servlet.addMapping("/");
      servlet.setLoadOnStartup(1);
      
      FilterRegistration charEncodingfilterReg = servletContext.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
      charEncodingfilterReg.setInitParameter("encoding", "UTF-8");
      charEncodingfilterReg.setInitParameter("forceEncoding", "true");
      charEncodingfilterReg.addMappingForUrlPatterns(null, false, "/*");
      
      
      
      
      
    }
}
