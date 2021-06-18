package at.htl.getAPet.model.datasource;

import javax.sql.DataSource;

public interface DataSourceFactory {

    public DataSource createDataSource();
}
