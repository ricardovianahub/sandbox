package net.ricardoviana.import2pg;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.*;

public class DataGenerator {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -jar DataGenerator.jar <JSON file path>");
            System.exit(1);
        }
        String jsonFilePath = args[0];
        parseAndProcess(jsonFilePath);
    }

    private static void parseAndProcess(String jsonFilePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File jsonFile = new File(jsonFilePath);
            Map<String, Object> params = mapper.readValue(jsonFile, Map.class);
            FileWriter writer = new FileWriter(jsonFilePath + ".sql");
            generateDDL(writer, params);
            String inputFile = (String) params.get("inputFile");
            if (inputFile.endsWith(".xls") || inputFile.endsWith(".xlsx")) {
                readExcelData(writer, inputFile, params);
            } else {
                loadData(writer, params);
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateDDL(FileWriter writer, Map<String, Object> params) {
        String tableName = (String) params.get("tableName");
        List<Map<String, String>> tableFields = (List<Map<String, String>>) params.get("tableFields");
        StringBuilder ddl = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (");
        for (Map<String, String> field : tableFields) {
            for (Map.Entry<String, String> entry : field.entrySet()) {
                ddl.append(entry.getKey()).append(" VARCHAR, ");
            }
        }
        ddl.setLength(ddl.length() - 2);
        ddl.append(");");
        try {
            writer.write(ddl.toString() + "\r\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        generateIndexes(writer, params, tableName);
    }

    private static void generateIndexes(FileWriter writer, Map<String, Object> params, String tableName) {
        List<List<String>> indexes = (List<List<String>>) params.get("indexes");
        for (int i = 0; i < indexes.size(); i++) {
            List<String> indexFields = indexes.get(i);
            StringBuilder indexSql = new StringBuilder("CREATE INDEX IF NOT EXISTS ");
            indexSql.append(tableName).append("_idx_").append(i).append(" ON ").append(tableName).append("(");
            for (String field : indexFields) {
                indexSql.append(field).append(", ");
            }
            indexSql.setLength(indexSql.length() - 2);
            indexSql.append(");");
            try {
                writer.write(indexSql.toString() + "\r\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void loadData(FileWriter writer, Map<String, Object> params) {
        String tableName = (String) params.get("tableName");
        List<Map<String, String>> tableFields = (List<Map<String, String>>) params.get("tableFields");
        String inputFile = (String) params.get("inputFile");
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                StringBuilder sql = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
                for (Map<String, String> field : tableFields) {
                    for (Map.Entry<String, String> entry : field.entrySet()) {
                        sql.append(entry.getKey()).append(", ");
                    }
                }
                sql.setLength(sql.length() - 2);
                sql.append(") VALUES (");
                for (String value : values) {
                    sql.append("'").append(value.trim().replace("'", "''")).append("', ");
                }
                sql.setLength(sql.length() - 2);
                sql.append(");");
                writer.write(sql.toString() + "\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readExcelData(FileWriter writer, String inputFile, Map<String, Object> params) throws IOException {
        Workbook workbook;
        try (InputStream inputStream = new FileInputStream(inputFile)) {
            workbook = inputFile.endsWith(".xlsx") ? new XSSFWorkbook(inputStream) : new HSSFWorkbook(inputStream);
        }

        Sheet sheet = workbook.getSheetAt(0);
        String tableName = (String) params.get("tableName");
        List<Map<String, String>> tableFields = (List<Map<String, String>>) params.get("tableFields");
        DataFormatter dataFormatter = new DataFormatter();

        for (Row row : sheet) {
            StringBuilder sql = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
            for (Map<String, String> field : tableFields) {
                for (Map.Entry<String, String> entry : field.entrySet()) {
                    sql.append(entry.getKey()).append(", ");
                }
            }
            sql.setLength(sql.length() - 2); // Remove the trailing comma and space
            sql.append(") VALUES (");

            for (Map<String, String> field : tableFields) {
                for (Map.Entry<String, String> entry : field.entrySet()) {
                    int columnIndex = excelColumnToIndex(entry.getValue());
                    Cell cell = row.getCell(columnIndex);
                    String cellValue = dataFormatter.formatCellValue(cell);
                    cellValue = cellValue.replace("\u2019", "'");
                    cellValue = cellValue.replace("'", "''");
                    sql.append("'").append(cellValue).append("', ");
                }
            }

            sql.setLength(sql.length() - 2); // Remove the trailing comma and space
            sql.append(");");
            writer.write(sql.toString() + "\r\n");
        }
    }

    private static int excelColumnToIndex(String column) {
        int index = 0;
        for (int i = 0; i < column.length(); i++) {
            index *= 26;
            index += column.charAt(i) - 'A' + 1;
        }
        return index - 1;
    }
}
