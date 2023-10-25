package PP231.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;


@Configuration
@ComponentScan("PP231.dao")
@PropertySource({"classpath:db.properties"})
@EnableTransactionManagement
public class DataBaseConfig {

    private Environment env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setJpaVendorAdapter(getJpaVendorAdapter());
        lcemfb.setDataSource(dataSource());
        lcemfb.setPersistenceUnitName("myJpaPersistenceUnit");
        lcemfb.setJpaProperties(hibernateProperties());
        lcemfb.setPackagesToScan("PP231");
        System.out.println("CONNECTED TO DATABASE");
        return lcemfb;
    }

    @Bean
    public JpaVendorAdapter getJpaVendorAdapter() {
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        return adapter;
    }
    @Bean(name = "transactionManager")
    public PlatformTransactionManager txManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(
                getEntityManagerFactoryBean().getObject());
        return jpaTransactionManager;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("db.driver")));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }
}
