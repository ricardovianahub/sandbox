package net.ricardoviana.import2pg;

public class SQLPrinter implements DatabaseWriter {
    @Override
    public void write(String sql) {
        System.out.println(sql);
    }
}
