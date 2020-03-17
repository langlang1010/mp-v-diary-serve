package cn.ailanglang.diary.config.dao;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
/**
 * @author smileyan
 */
@Configuration
public class SessionFactoryConfiguration {
    @Value("${mybatis_config_file}")
    private String mybatisCofigFilePath;
    @Value("${mybatis_mapper_path}")
    private String mapPath;

    @Autowired
    private DataSource dataSource;

    @Value("${entity_package}")
    @Qualifier("dataSource")
    private String entityPackage;

    @Bean(name="sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisCofigFilePath));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath=PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapPath;

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageSearchPath));
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(entityPackage);
        return sqlSessionFactoryBean;
    }
}
