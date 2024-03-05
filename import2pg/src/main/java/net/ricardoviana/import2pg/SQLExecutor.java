package net.ricardoviana.import2pg;

public class SQLExecutor implements DatabaseWriter {
    @Override
    public void write(String sql) {
        // Future implementation will execute the SQL against the database.
        System.out.println("This will execute: " + sql);
    }
}
