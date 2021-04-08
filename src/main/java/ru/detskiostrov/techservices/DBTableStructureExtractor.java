package ru.detskiostrov.techservices;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import ru.detskiostrov.virtuemart.JoonlaAccesCredentionals;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 *   Class and method used for extracting My SQL db table and safe it's structure as java class
 *   Author: Grigorii Andreev
 *   Date: 35 March 2021
 */

public class DBTableStructureExtractor {

    // Be careful to store secure and private data
    private static final String shopDBName = "db1029682_integ2";
    private static final String dbTableName = "`dqope_virtuemart_order_items`";
    private static final String extractedDataFolder = "D:/JavaStudy/UsefullUtils/src/main/resources/resultfolder/";
    private static final String extractedDataExcelFileName = "db_" + JoonlaAccesCredentionals.shopDBName +
            "_table_" + JoonlaAccesCredentionals.dbTableName + "_structure.xls";
    private static final String shopDBUrl = "jdbc:mysql://" + JoonlaAccesCredentionals.shopDBIP + ":3306/" +
            JoonlaAccesCredentionals.shopDBName;

    static List<DBTableStructureExtractor> dbTableStructure = new ArrayList();
    static DBTableStructureExtractor item;

    private String table_cat;
    private String table_schem;
    private String table_name;
    private String column_name;
    private String data_type;
    private String type_name;
    private String column_size;
    private String decimal_digits;
    private String nullable;

    public String getTable_cat() {
        return table_cat;
    }

    public void setTable_cat(String table_cat) {
        this.table_cat = table_cat;
    }

    public String getTable_schem() {
        return table_schem;
    }

    public void setTable_schem(String table_schem) {
        this.table_schem = table_schem;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getColumn_size() {
        return column_size;
    }

    public void setColumn_size(String column_size) {
        this.column_size = column_size;
    }

    public String getDecimal_digits() {
        return decimal_digits;
    }

    public void setDecimal_digits(String decimal_digits) {
        this.decimal_digits = decimal_digits;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

    public static void main(String[] args) throws IOException {
        dbTableStructure = extractTableStructure(dbTableName);
        safeTablesStructureToExcelFile();
        translateMySQLTableIntoJavaClass();
    }

    public static List extractTableStructure(String dbTableName) {
        List<DBTableStructureExtractor> dbTableStructure = new ArrayList();
        DBTableStructureExtractor item;

        Connection connection;
        try {
            connection = DriverManager.getConnection(shopDBUrl, JoonlaAccesCredentionals.shopDBAdminLogin,
                    JoonlaAccesCredentionals.shopDBAdminPassword);
            System.out.println("Connection to db is established!");

            DatabaseMetaData information = connection.getMetaData();
            ResultSet res = information.getColumns(null, null, dbTableName, null);
                while (res.next()) {
                    item = new DBTableStructureExtractor();

                    item.setTable_cat(res.getString("TABLE_CAT"));
                    item.setTable_schem(res.getString("TABLE_SCHEM"));
                    item.setTable_name(res.getString("TABLE_NAME"));
                    item.setColumn_name(res.getString("COLUMN_NAME"));
                    item.setData_type(res.getString("DATA_TYPE"));
                    item.setType_name(res.getString("TYPE_NAME"));
                    item.setColumn_size(res.getString("COLUMN_SIZE"));
                    item.setDecimal_digits(res.getString("DECIMAL_DIGITS"));
                    item.setNullable(res.getString("NULLABLE"));
                    System.out.println("Column name " + item.getColumn_name());
                    dbTableStructure.add(item);
                }

            } catch (SQLException throwable) {
            throwable.printStackTrace();
            System.out.println("Something wrong with connection!");
            System.out.println("Please, check your IP, login, password and db name!");
        }
        return dbTableStructure;
    }


    // Method to safe db table structure to Excel file
    public static void safeTablesStructureToExcelFile() {

        // Create Excel variables
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Table " + dbTableName + " structure");
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        int excelRowsCounter = 0;

        // Create 0-th Excel row (the columns names) and set the name of columns
        Row row = sheet.createRow(excelRowsCounter);
        row.createCell(0).setCellValue("TABLE_CAT");
        row.createCell(1).setCellValue("TABLE_SCHEM");
        row.createCell(2).setCellValue("TABLE_NAME");
        row.createCell(3).setCellValue("COLUMN_NAME");
        row.createCell(4).setCellValue("DATA_TYPE");
        row.createCell(5).setCellValue("TYPE_NAME");
        row.createCell(6).setCellValue("COLUMN_SIZE");
        row.createCell(7).setCellValue("DECIMAL_DIGITS");

        //Fill all rows for exel result table
        System.out.println("Filling Excel file = ");
        excelRowsCounter = 1;
        for (DBTableStructureExtractor line : dbTableStructure) {
            Row currentExcelRow = sheet.createRow(excelRowsCounter);
//            System.out.println(line.getColumn_name());

            currentExcelRow.createCell(0).setCellValue(line.getTable_cat());
            currentExcelRow.createCell(1).setCellValue(line.getTable_schem());
            currentExcelRow.createCell(2).setCellValue(line.getTable_name());
            currentExcelRow.createCell(3).setCellValue(line.getColumn_name());
            currentExcelRow.createCell(4).setCellValue(line.getData_type());
            currentExcelRow.createCell(5).setCellValue(line.getType_name());
            currentExcelRow.createCell(6).setCellValue(line.getColumn_size());
            currentExcelRow.createCell(7).setCellValue(line.getDecimal_digits());

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

    //Method to translate MySQL table structure into Java class
    public static void translateMySQLTableIntoJavaClass() throws IOException {

        FileWriter writer = new FileWriter(extractedDataFolder + dbTableName + "_JavaClass.class");

        String receivedDataType;
        String javaDataType = "";
        String javaVariableName;
        writer.write("\\\\There is a comments about class purpose\n");
        writer.write("public class " + renameMySQLTableNameToJavaStyleClassName(dbTableName) + " {");
        writer.write("\n\n");

        for (DBTableStructureExtractor line : dbTableStructure) {
            receivedDataType = line.getType_name();
            if (receivedDataType.contains("INT")) javaDataType = "int";
            if (receivedDataType.contains("TINYINT")) javaDataType = "byte";
            if (receivedDataType.contains("SMALLINT")) javaDataType = "short";
            if (receivedDataType.contains("VARCHAR")) javaDataType = "String";
            if (receivedDataType.contains("CHAR(1)")) javaDataType = "char";
            javaVariableName = line.getColumn_name();
            String javaCorrectName = javaVariableName;

            if(javaVariableName.contains("_")) {
                javaCorrectName = renameMySQLTableColumnNameToJavaStyleVariableName(javaVariableName);
            }

            writer.write("\t" + javaDataType + " ");
            writer.write(javaCorrectName + ";\n");
        }
        writer.write("} \n");
        writer.close();
        System.out.println("Java class saved!");

    }

    // Method is renaming My SQL table names to Java style names (remove '_' and convert next symbol to uppercase
    public static String renameMySQLTableColumnNameToJavaStyleVariableName (String javaVariableName ) {

        boolean nameCorrect = false;
        int indexOfUnderlineSymbol;
        String javaVariableNameTemp;
        char[] charArray;


        while (!nameCorrect) {

            indexOfUnderlineSymbol = javaVariableName.indexOf("_");
            javaVariableNameTemp = javaVariableName.replaceFirst("_", "");
            charArray = javaVariableNameTemp.toCharArray();
            charArray[indexOfUnderlineSymbol] = Character.toUpperCase(charArray[indexOfUnderlineSymbol]);
            javaVariableName = new String(charArray);

            if(javaVariableName.contains("_")) {
                nameCorrect = false;
            } else {
                nameCorrect = true;
            }

        }

        return javaVariableName;
    }

    // Rename the My SQL Table name to java Style class name (first letter is uppersace,
    // remove '_' sign and next letter is uppercase)
    public static String renameMySQLTableNameToJavaStyleClassName (String mySQLTableName) {

        boolean nameCorrect = false;
        int indexOfUnderlineSymbol;
        String javaVariableNameTemp;
        char[] charArray;

        while (!nameCorrect) {
            indexOfUnderlineSymbol = mySQLTableName.indexOf("_");
            javaVariableNameTemp = mySQLTableName.replaceFirst("_", "");
            charArray = javaVariableNameTemp.toCharArray();
            charArray[0] = Character.toUpperCase(charArray[0]);
            charArray[indexOfUnderlineSymbol] = Character.toUpperCase(charArray[indexOfUnderlineSymbol]);
            mySQLTableName = new String(charArray);

            if(mySQLTableName.contains("_")) {
                nameCorrect = false;
            } else {
                nameCorrect = true;
            }
        }
        return mySQLTableName;
    }
}
