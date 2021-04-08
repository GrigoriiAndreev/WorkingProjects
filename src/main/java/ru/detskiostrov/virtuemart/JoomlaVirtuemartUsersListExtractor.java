/*
 *  Class extracts the Joomla users from MySQL db and safe them into Excel file
 *  Author: Grigorii Andreev
 *  Date: 26 March 2021
 */

package ru.detskiostrov.virtuemart;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoomlaVirtuemartUsersListExtractor {

    // Be careful to store secure and private data
    private static final String extractedDataFolder = "D:/JavaStudy/UsefullUtils/src/main/resources/resultfolder/";
    private static final String extractedDataExcelFileName = "db_" + JoonlaAccesCredentionals.shopDBName + "users_list.xls";
    private static final String dbSQLQueryToGetUsersList = "select * from dqope_users";
    private static final String shopDBUrl = "jdbc:mysql://" + JoonlaAccesCredentionals.shopDBIP +
            ":3306/" + JoonlaAccesCredentionals.shopDBName;

    static List<JoomlaVirtuemartUsers> joomlaVirtuemartUsersList = new ArrayList<JoomlaVirtuemartUsers>();

    public static void main(String[] args) throws SQLException {

        extractJoomlaUsers();
        safeUsersListToExcelFile();

    }

    //Method connects to db via jdbc and extract to List all users
    public static void extractJoomlaUsers() throws SQLException {

        List<JoomlaVirtuemartUsers> allUsersList = new ArrayList();

        //Establish the connection via JDBC
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        try (Connection connection = DriverManager.getConnection(shopDBUrl,
                JoonlaAccesCredentionals.shopDBAdminLogin, JoonlaAccesCredentionals.shopDBAdminPassword);
             Statement statement = connection.createStatement()) {
                System.out.println("The connection to DB is established!");
                ResultSet resultSet = statement.executeQuery(dbSQLQueryToGetUsersList);

                while (resultSet.next()) {
                    JoomlaVirtuemartUsers singleUser = new JoomlaVirtuemartUsers();

                    singleUser.id = resultSet.getInt(1);
                    singleUser.name = resultSet.getString(2);
                    singleUser.username = resultSet.getString(3);
                    singleUser.email = resultSet.getString(4);
                    singleUser.usertype = resultSet.getString(5);
                    singleUser.activation = resultSet.getString(10);
                    singleUser.params = resultSet.getString(11);
                    singleUser.lastResetTime = resultSet.getString(12);

                    allUsersList.add(singleUser);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        System.out.println("All users has been extracted.");
        joomlaVirtuemartUsersList = allUsersList;

    }

    //Methods safes all Joomla users into the Excel file
    public static void safeUsersListToExcelFile() {

        // Create Excel variables
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Users list");
        int excelRowsCounter = 0;

        // Create 0-th Excel row (the columns names) and set the name of columns
        Row row = sheet.createRow(excelRowsCounter);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("name");
        row.createCell(3).setCellValue("username");
        row.createCell(4).setCellValue("email");
        row.createCell(5).setCellValue("password");
        row.createCell(6).setCellValue("usertype");
        row.createCell(7).setCellValue("block");
        row.createCell(8).setCellValue("sendEmail");
        row.createCell(9).setCellValue("registerDate");
        row.createCell(10).setCellValue("lastvisitDate");
        row.createCell(11).setCellValue("activation");
        row.createCell(12).setCellValue("params");
        row.createCell(13).setCellValue("lastResteTime");
        row.createCell(14).setCellValue("restCount");

        //Fill all rows for excel result table
        excelRowsCounter = 1;

//        System.out.println(joomlaVirtuemartUsersList.get(3).);

        for (JoomlaVirtuemartUsers user : joomlaVirtuemartUsersList) {
            Row currentExcelRow = sheet.createRow(excelRowsCounter);

            currentExcelRow.createCell(0).setCellValue(user.id);
            currentExcelRow.createCell(1).setCellValue(user.name);
            currentExcelRow.createCell(2).setCellValue(user.username);
            currentExcelRow.createCell(3).setCellValue(user.email);
            currentExcelRow.createCell(4).setCellValue(user.password);
            currentExcelRow.createCell(5).setCellValue(user.usertype);
            currentExcelRow.createCell(6).setCellValue(user.block);
            currentExcelRow.createCell(7).setCellValue(user.sendEmail);
            currentExcelRow.createCell(8).setCellValue(user.registerDate);
            currentExcelRow.createCell(9).setCellValue(user.lastvisitDate);
            currentExcelRow.createCell(10).setCellValue(user.activation);
            currentExcelRow.createCell(11).setCellValue(user.params);
            currentExcelRow.createCell(12).setCellValue(user.lastResetTime);
            currentExcelRow.createCell(13).setCellValue(user.resetCount);
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
