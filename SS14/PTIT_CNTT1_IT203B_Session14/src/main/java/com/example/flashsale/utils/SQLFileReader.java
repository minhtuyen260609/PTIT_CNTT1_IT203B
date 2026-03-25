package com.example.flashsale.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;

/**
 * SQLFileReader - Đọc SQL scripts từ file trong resources folder
 * Dùng để chạy script khởi tạo database (schema, data, procedures)
 */
public class SQLFileReader {
    /**
     * Đọc SQL script từ file trong resources folder
     * 
     * @param fileName Tên file (VD: "sql/schema.sql")
     * @return Nội dung SQL script
     */
    public static String readSQLScript(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();

        try (InputStream input = SQLFileReader.class.getClassLoader()
                .getResourceAsStream(fileName);
                InputStreamReader inputStreamReader = new InputStreamReader(input);
                BufferedReader reader = new BufferedReader(inputStreamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Bỏ qua comment và dòng trống
                if (!line.trim().startsWith("--") && !line.trim().isEmpty()) {
                    content.append(line).append("\n");
                }
            }
        }

        return content.toString();
    }

    /**
     * Tách SQL statements từ content (các statement cách nhau bằng dấu ;)
     * 
     * @param sqlContent SQL content string
     * @return Mảng các SQL statements
     */
    public static String[] splitStatements(String sqlContent) {
        if (sqlContent == null || sqlContent.isEmpty()) {
            return new String[0];
        }

        // Tách bằng dấu ;
        String[] statements = sqlContent.split(";");

        // Trim và lọc các statement rỗng
        java.util.List<String> result = new java.util.ArrayList<>();
        for (String stmt : statements) {
            String trimmed = stmt.trim();
            if (!trimmed.isEmpty()) {
                result.add(trimmed);
            }
        }

        return result.toArray(new String[0]);
    }
}
