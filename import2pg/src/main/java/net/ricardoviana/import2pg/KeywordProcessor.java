package net.ricardoviana.import2pg;

import org.json.JSONArray;
import org.json.JSONObject;

public class KeywordProcessor {
    public static String extractFieldsFromHeaders(String headers) {
        String[] keywords = headers.split("\\t");

        JSONArray jsonArray = new JSONArray();
        int columnIndex = 0;

        for (String keyword : keywords) {
            String snakeCaseKeyword = keyword
                    .toLowerCase()
                    .replaceAll("%", "pct")
                    .replaceAll("[^a-z0-9\\s]", "")
                    .trim()
                    .replaceAll("\\s+", "_");

            if (snakeCaseKeyword.length() > 30) {
                snakeCaseKeyword = snakeCaseKeyword.substring(0, 30);
            }

            if (Character.isDigit(snakeCaseKeyword.charAt(0))) {
                snakeCaseKeyword = "n" + snakeCaseKeyword;
            }

            String excelColumnName = columnIndexToExcelColumnName(columnIndex);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(snakeCaseKeyword, excelColumnName);

            jsonArray.put(jsonObject);

            columnIndex++;
        }

        return jsonArray.toString();
    }

    private static String columnIndexToExcelColumnName(int index) {
        StringBuilder columnName = new StringBuilder();
        while (index >= 0) {
            int remainder = index % 26;
            columnName.insert(0, (char) ('A' + remainder));
            index = (index / 26) - 1;
        }
        return columnName.toString();
    }

    public static void main(String[] args) {
        String header = "SOC\tDescription\t 2021 Jobs \t2031 Jobs\t2021 - 2031 Change\t2021 - 2031 % Change\tAnnual Openings\tRegional Completions (2020)\t Median Hourly Earnings \t(Pain Matrix Right)\tWork from home\tStress level\tPay\tEducation Needed\tHours Worked/ Demand\tSafety/ physical Demand\tPain (5 scale)\n";
        System.out.println(extractFieldsFromHeaders(header));
    }
}
