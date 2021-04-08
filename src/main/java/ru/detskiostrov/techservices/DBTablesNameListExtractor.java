package ru.detskiostrov.techservices;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/*
 *  Class is extracting the list of tables from given database and safe them to given file name as Excel file
 *  Author: Grigorii Andreev
 *  Date: 23 March 2021
 */

public class DBTablesNameListExtractor {

    // Be careful to store secure and private data
    private static final String shopDBAdminLogin = "u1029682_integ2";
    private static final String shopDBAdminPassword = "123qweQWE";
    private static final String shopDBIP = "5.253.60.105";
    private static final String shopDBName = "db1029682_integ2";
    private static final String extractedDataFolder = "D:/JavaStudy/UsefullUtils/src/main/resources/resultfolder/";
    private static final String extractedDataExcelFileName = "db_" + shopDBName + "_tablesnameslist.xls";
    private static final String shopDBUrl = "jdbc:mysql://" + shopDBIP + ":3306/" + shopDBName;

    static final List<DBTablesNameListExtractor> allTablesNameList = new ArrayList<>();
    static DBTablesNameListExtractor item;

    // Variables named as db notation
    private String table_cat;
    private String table_schem;
    private String table_name;
    private String table_type;



    public static void main(String[] args) {

        extractAllTablesNameInDB();
        safeTablesNameListToExcelFile();
    }

    public static void extractAllTablesNameInDB() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(shopDBUrl, shopDBAdminLogin, shopDBAdminPassword);
            System.out.println("Connection to db is established!");

            DatabaseMetaData information = connection.getMetaData();
            String[] allTablesNameTemp = { "TABLE" };
            ResultSet resultSet = information.getTables(null, null, null, allTablesNameTemp);
            while (resultSet.next()) {
                item = new DBTablesNameListExtractor();
                item.table_cat = resultSet.getString(1);
                item.table_schem = resultSet.getString(2);
                item.table_name = resultSet.getString(3);
                item.table_type = resultSet.getString(4);

                allTablesNameList.add(item);
            }
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something wrong with connection!");
            System.out.println("Please, check your IP, login, password and db name!");
        }
    }

    public static void safeTablesNameListToExcelFile() {

        // Create Excel variables
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Tables list");
        int excelRowsCounter = 0;

        // Create 0-th Excel row (the columns names) and set the name of columns
        Row row = sheet.createRow(excelRowsCounter);
        row.createCell(0).setCellValue("DB name");
        row.createCell(1).setCellValue("Catalog of the schema");
        row.createCell(2).setCellValue("Tables names");
        row.createCell(3).setCellValue("Type of the table");

        //Fill all rows for exel result table
        excelRowsCounter = 1;
        for (DBTablesNameListExtractor line : allTablesNameList) {
            Row currentExcelRow = sheet.createRow(excelRowsCounter);
            currentExcelRow.createCell(0).setCellValue(line.table_cat);
            currentExcelRow.createCell(1).setCellValue(line.table_schem);
            currentExcelRow.createCell(2).setCellValue(line.table_name);
            currentExcelRow.createCell(3).setCellValue(line.table_type);

            excelRowsCounter++;
        }

        // Safe extracted data to xls file
        try (FileOutputStream out = new FileOutputStream(extractedDataFolder + extractedDataExcelFileName)) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Excel file is created!");
    }


}
