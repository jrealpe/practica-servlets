/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package c3p0.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class Datasource {
    
    private final Properties props;
    private final ComboPooledDataSource cpds;
    private static Datasource datasource;

    public Datasource() throws IOException, SQLException {
        // load datasource properties
        //log.info("Reading datasource.properties from classpath");
        
        props = Utils.readProperties("datasource.properties");
        cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl(props.getProperty("jdbc.url"));
        cpds.setUser(props.getProperty("jdbc.username"));
        cpds.setPassword(props.getProperty("jdbc.password"));
        
        cpds.setInitialPoolSize(new Integer((String) props.getProperty("initialPoolSize")));
        cpds.setAcquireIncrement(new Integer((String) props.getProperty("acquireIncrement")));
        cpds.setMaxPoolSize(new Integer((String) props.getProperty("maxPoolSize")));
        cpds.setMinPoolSize(new Integer((String) props.getProperty("minPoolSize")));
        cpds.setMaxStatements(new Integer((String) props.getProperty("maxStatements")));
        cpds.setIdleConnectionTestPeriod(new Integer((String) props.getProperty("idle_test_period")));
        
    }
    
    public static Datasource getInstance() throws IOException, SQLException {
        if (datasource == null) {
            datasource = new Datasource();
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }
    
}
