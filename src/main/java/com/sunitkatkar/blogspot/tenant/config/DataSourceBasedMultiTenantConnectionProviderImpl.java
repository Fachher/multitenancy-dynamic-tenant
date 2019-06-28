package com.sunitkatkar.blogspot.tenant.config;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.sunitkatkar.blogspot.master.model.MasterTenant;
import com.sunitkatkar.blogspot.master.repository.MasterTenantRepository;
import com.sunitkatkar.blogspot.util.DataSourceUtil;

@Configuration
public class DataSourceBasedMultiTenantConnectionProviderImpl
        extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    private static final Logger LOG = LoggerFactory.getLogger(DataSourceBasedMultiTenantConnectionProviderImpl.class);

    private static final long serialVersionUID = 1L;

    @Autowired
    private MasterTenantRepository masterTenantRepo;

    @Autowired
    private DataSource masterDatasource;

    private Map<String, DataSource> dataSourcesMtApp = new TreeMap<>();

    @Override
    protected DataSource selectAnyDataSource() {
        return masterDatasource;
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        if (!this.dataSourcesMtApp.containsKey(tenantIdentifier)) {
            List<MasterTenant> masterTenants = masterTenantRepo.findAll();
            LOG.info(
                    ">>>> selectDataSource() -- tenant:" + tenantIdentifier + " Total tenants:" + masterTenants.size());
            for (MasterTenant masterTenant : masterTenants) {
                dataSourcesMtApp.put(masterTenant.getTenantId(),
                        DataSourceUtil.createAndConfigureDataSource(masterTenant));
            }
        }
        return this.dataSourcesMtApp.get(tenantIdentifier);
    }
}