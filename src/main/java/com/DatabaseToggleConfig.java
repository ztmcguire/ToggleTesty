package com;

import org.togglz.core.Feature;
import org.togglz.core.manager.TogglzConfig;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.jdbc.JDBCStateRepository;
import org.togglz.core.user.SimpleFeatureUser;
import org.togglz.core.user.UserProvider;

import javax.sql.DataSource;

public class DatabaseToggleConfig implements TogglzConfig {

    private DataSource ds;

    public static DatabaseToggleConfig create(DataSource ds){
        return new DatabaseToggleConfig(ds);
    }

    public DatabaseToggleConfig(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Class<? extends Feature> getFeatureClass() {
        return Togglz.class;
    }

    @Override
    public StateRepository getStateRepository() {
        return new JDBCStateRepository(ds, "toggle");
    }

    @Override
    public UserProvider getUserProvider() {
        return () -> new SimpleFeatureUser("admin", true);
    }

}