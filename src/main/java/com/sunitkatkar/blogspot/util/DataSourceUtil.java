package com.sunitkatkar.blogspot.util;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sunitkatkar.blogspot.master.model.MasterTenant;
import com.zaxxer.hikari.HikariDataSource;

public final class DataSourceUtil {

    private static final Logger LOG = LoggerFactory
            .getLogger(DataSourceUtil.class);

    public static DataSource createAndConfigureDataSource(
            MasterTenant masterTenant) {
        HikariDataSource ds = new HikariDataSource();
        ds.setUsername(masterTenant.getUsername());
        ds.setPassword(masterTenant.getPassword());
        ds.setJdbcUrl(masterTenant.getUrl());
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setConnectionTimeout(20000);
        ds.setMinimumIdle(10);
        ds.setMaximumPoolSize(20);
        ds.setIdleTimeout(300000);
        ds.setConnectionTimeout(20000);

        String tenantId = masterTenant.getTenantId();
        String tenantConnectionPoolName = tenantId + "-connection-pool";
        ds.setPoolName(tenantConnectionPoolName);
        LOG.info("Configured datasource:" + masterTenant.getTenantId()
                + ". Connection poolname:" + tenantConnectionPoolName);
        return ds;
    }
}
