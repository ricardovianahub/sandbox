package net.ricardoviana.import2pg;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.List;
import java.util.Map;

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
            generateDDL(params);
            loadData(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateDDL(Map<String, Object> params) {
        String tableName = (String) params.get("tableName");
        List<Map<String, String>> tableFields = (List<Map<String, String>>) params.get("tableFields");
        StringBuilder ddl = new StringBuilder("CREATE TABLE ").append(tableName).append(" (");
        for (Map<String, String> field : tableFields) {
            for (Map.Entry<String, String> entry : field.entrySet()) {
                ddl.append(entry.getKey()).append(" VARCHAR, ");
            }
        }
        ddl.setLength(ddl.length() - 2);
        ddl.append(");");
        System.out.println(ddl);
    }

    private static void loadData(Map<String, Object> params) {
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
                for (Map<String, String> field : tableFields) {
                    for (Map.Entry<String, String> entry : field.entrySet()) {
                        int columnIndex = excelColumnToIndex(entry.getValue());
                        sql.append("'").append(values[columnIndex].trim()).append("', ");
                    }
                }
                sql.setLength(sql.length() - 2);
                sql.append(");");
                System.out.println(sql.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
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
