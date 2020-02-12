package com.example.demo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
            String sheetName = "test";

            Sheet sheet = workbook.getSheet(sheetName);

            Map<Integer, List<String>> map = parseXLSXToMap(workbook, sheet);
            map.entrySet().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, List<String>> parseXLSXToMap(Workbook workbook, Sheet sheet) {
        //finding out the max number of cells in a row
        int maxCellNum = 0;
        for (Row row : sheet) {
            if (maxCellNum < row.getLastCellNum()) maxCellNum = row.getLastCellNum();
        }

        System.out.println("Parsing started: " + sheet.getLastRowNum() + " rows, " + maxCellNum + " cells");


        System.out.println(sheet.getNumMergedRegions());
        System.out.println(Arrays.toString(sheet.getMergedRegions().toArray()));
        System.out.println(sheet.getMergedRegion(2));





        Map<Integer, List<String>> data = new HashMap<>();
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
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
                        data.get(i).add("#BOOLEAN");
                        break;
                    case FORMULA:
                        data.get(i).add("#FORMULA");
                        break;
                    case BLANK:
                        data.get(i).add("0.0");
                        break;
                    case _NONE:
                        data.get(i).add("#_NONE");
                        break;
                    case ERROR:
                        data.get(i).add("#ERROR");
                        break;
                    default:
                        data.get(i).add("#VALUE");
                }
            }
        }
        return data;
    }
}