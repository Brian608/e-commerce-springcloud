package org.feather.ecommerce.conf;

import com.zaxxer.hikari.HikariDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.conf
 * @className: DataSourceProxyAutoConfiguration
 * @author: feather
 * @description: Seata 所需要的数据源代理配置类
 * @since: 2023-09-10 9:40
 * @version: 1.0
 */

@Configuration
public class DataSourceProxyAutoConfiguration {

    private final DataSourceProperties dataSourceProperties;

    public DataSourceProxyAutoConfiguration(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    /**
     * <h2>配置数据源代理, 用于 Seata 全局事务回滚</h2>
     * before image + after image -> undo_log
     * */
    @Primary
    @Bean("dataSource")
    public DataSource dataSource() {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());

        return new DataSourceProxy(dataSource);
    }
}
