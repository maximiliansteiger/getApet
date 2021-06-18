package at.htl.getAPet.model.datasource;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class SimpleDataSourceFactory implements DataSourceFactory {

    protected static final String SERVER_NAME = "localhost";
    protected static final String DATABASE_NAME = "postgres";
    protected static final String USER = "postgres";
    protected static final String PASSWORD = "postgres";

    protected final String schema;

    public SimpleDataSourceFactory(String schema) {
        this.schema = schema;
    }

    public DataSource createDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerNames(new String[]{SERVER_NAME});
        dataSource.setDatabaseName(DATABASE_NAME);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        if (!schema.isEmpty()) {
            dataSource.setCurrentSchema(schema);
        }
        return dataSource;
    }
}
