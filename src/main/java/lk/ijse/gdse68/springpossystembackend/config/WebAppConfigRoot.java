package lk.ijse.gdse68.springpossystembackend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.EntityManagerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author : sachini
 * @date : 2024-10-11
 **/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "lk.ijse.gdse68.springpossystembackend")
@EnableJpaRepositories(basePackages = "lk.ijse.gdse68.springpossystembackend")
@EnableTransactionManagement
public class WebAppConfigRoot {
    @Bean
    public ModelMapper modelMapper(){ // source for bean definition -used for object mapping
        return new ModelMapper();
    }

    @Bean
    public DataSource dataSource(){ //bean using MySQL, setting the JDBC URL, username, password, and driver class.
        var dmds =  new DriverManagerDataSource();
        dmds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dmds.setUrl("jdbc:mysql://localhost:3306/pos_spring_v2?createDatabaseIfNotExist=true");
        dmds.setUsername("root");
        dmds.setPassword("ijse@2001");
        return dmds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() { //using Hibernate as the JPA provider and configures the entity scanning package
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);


        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("lk.ijse.gdse68.springpossystembackend.entity");
        factory.setDataSource(dataSource());
        return factory;
    }
    @Bean  //Configures the transaction management with manages transactions for the JPA-based application.
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }


}
