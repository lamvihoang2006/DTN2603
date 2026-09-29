package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class CsvHelper {

    public interface CsvRowProcessor {
        void process(String[] data) throws Exception;
    }

    public static String importCsvGeneric(String filePath, int minColumns, CsvRowProcessor processor) throws Exception {
        if (!filePath.toLowerCase().endsWith(".csv")) {
            throw new Exception("File không hợp lệ! Vui lòng cung cấp file có đuôi .csv");
        }

        File inputFile = new File(filePath);
        if (!inputFile.exists()) {
            throw new Exception("File không tồn tại! Vui lòng kiểm tra lại đường dẫn.");
        }

        String errorFilePath = inputFile.getParent() + "/error_" + inputFile.getName();
        int successCount = 0;
        int errorCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(errorFilePath))) {

            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    bw.write(line + ",Error_Message\n");
                    isFirstLine = false;
                    continue;
                }

                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");
                if (data.length < minColumns) {
                    bw.write(line + ",Thiếu cột dữ liệu\n");
                    errorCount++;
                    continue;
                }

                try {
                    processor.process(data);
                    successCount++;
                } catch (Exception e) {
                    bw.write(line + "," + e.getMessage() + "\n");
                    errorCount++;
                }
            }
        }

        if (errorCount == 0) {
            new File(errorFilePath).delete();
            return "Import thành công " + successCount + " bản ghi. Không có lỗi.";
        } else {
            return "Import thành công " + successCount + " bản ghi. Có " + errorCount + " bản ghi lỗi.\n-> Đã xuất file lỗi tại: " + errorFilePath;
        }
    }
}