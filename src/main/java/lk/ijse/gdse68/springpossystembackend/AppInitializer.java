package lk.ijse.gdse68.springpossystembackend;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import lk.ijse.gdse68.springpossystembackend.config.WebAppConfig;
import lk.ijse.gdse68.springpossystembackend.config.WebAppConfigRoot;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/**
 * @author : sachini
 * @date : 2024-10-11
 **/
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * @return the configuration classes for the root application context
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebAppConfigRoot.class};
    }

    /**
     * @return the configuration classes for the servlet application context
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebAppConfig.class};
    }

    /**
     * @return the servlet mapping for the DispatcherServlet
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        String tempDir = System.getProperty("java.io.tmpdir");
        registration.setMultipartConfig(new MultipartConfigElement(tempDir));
    }
}
