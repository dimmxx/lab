package com.example.demo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        try {
            FileInputStream excelFile = new FileInputStream(new File("src/main/resources/parse.xlsx"));
            Workbook workbook = new XSSFWorkbook(excelFile);
            String sheetName = "matrix_1";

            Sheet sheet = workbook.getSheet(sheetName);

            Map<Integer, List<String>> map = parseWithFor(workbook, sheet, "");
            map.entrySet().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, List<String>> parseWithFor(Workbook workbook, Sheet sheet, String marker) {
        //finding out the max number of cells in a row
        int maxCellNum = 0;
        for(Row row : sheet){
            if(maxCellNum < row.getLastCellNum()) maxCellNum = row.getLastCellNum();
        }

        System.out.println(sheet.getLastRowNum());
        System.out.println(maxCellNum);

        Map<Integer, List<String>> data = new HashMap<>();
        for (int i = 0; i <= sheet.getLastRowNum() ; i++) {
            Row row = sheet.getRow(i);
            if(row == null){
                sheet.createRow(i);
                row = sheet.getRow(i);
            }
            data.put(i, new ArrayList<String>());
            for (int j = 0; j <= maxCellNum; j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    row.createCell(j);
                    data.get(i).add("#NULL");
                    continue;
                }
                switch (cell.getCellType()) {
                    case STRING:
                        data.get(i).add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        data.get(i).add(String.valueOf(cell.getNumericCellValue()));
                        break;
                    case BOOLEAN:
                        break;
                    case FORMULA:
                        break;
                    default:
                        data.get(i).add("#VALUE");
                }
            }
        }
        return data;
    }
}