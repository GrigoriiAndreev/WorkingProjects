package ru.detskiostrov.addresses;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import ru.detskiostrov.virtuemart.JoonlaAccesCredentionals;
import ru.detskiostrov.virtuemart.VirtuemartOrderList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for extracting clients list from joomla based sites and safe the list to excel file
 * Author: Grigorii Andreev
 * Data: 11 March 2011
 */
public class ClientsAddressBook {

    //Be careful to publish private and secure info in OpenSource places!!!
    //Data to extract users list from Joomla sites
    private static final String extractedDataFolder = "D:/JavaStudy/UsefullUtils/src/main/resources/resultfolder/";
    private static final String extractedDataExcelFileName = "AllUsersList.xls";
    private static final String siteDBUrl = "jdbc:mysql://" + JoonlaAccesCredentionals.shopDBIP +
            ":3306/" + JoonlaAccesCredentionals.shopDBName;
    private static final String dbSQLQueryToGetUsersList = "select * from dqope_users";

    //Data to extract orders from Virtuemart shops
    static List<ClientsAddressBook> allShopUsersList = new ArrayList<>();
    ClientsAddressBook clientsAddressBook;

    static List<VirtuemartOrderList> virtuemartOrderList = new ArrayList<>();
    VirtuemartOrderList virtuemartSingleOrder;

    int clientID;
    String clientEmail;
    String clientName;
    String clientUserName;
    String clientTelephone;
    String purchasesNumber;
    String accountCreatedData;
    Date lastPurchase;

    //Getters and setters
    public ClientsAddressBook() {
    }

    public ClientsAddressBook(int clientID, String clientEmail, String clientName) {
        this.clientID = clientID;
        this.clientEmail = clientEmail;
        this.clientName = clientName;
    }

    @Override
    public String toString() {
        return "ClientsAddressBook{" +
                "clientID=" + clientID +
                ", clientEmail='" + clientEmail + '\'' +
                ", clientName='" + clientName + '\'' +
                '}';
    }

    public static void connectToDBUsersList(String shopDBUrl, String shopDBAdminLogin, String shopDBAdminPassword) throws SQLException {

        //Establish the connection via JDBC
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        try (Connection connection = DriverManager.getConnection(JoonlaAccesCredentionals.shopDBIP,
                JoonlaAccesCredentionals.shopDBAdminLogin, JoonlaAccesCredentionals.shopDBAdminPassword);
             Statement statement = connection.createStatement()) {
            System.out.println("The connection to DB is established!");
            ResultSet resultSet = statement.executeQuery(dbSQLQueryToGetUsersList);

            while (resultSet.next()) {
                ClientsAddressBook singleUser = new ClientsAddressBook();

                singleUser.clientID = resultSet.getInt(1);
                singleUser.clientName = resultSet.getString(2);
                singleUser.clientUserName = resultSet.getString(3);
                singleUser.clientEmail = resultSet.getString(4);
                singleUser.accountCreatedData = resultSet.getString(8);

                allShopUsersList.add(singleUser);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("All users successfully extracted from db " + JoonlaAccesCredentionals.shopDBName);
    }

    public static void safeUsersListToExcelFile() {

        // создание самого excel файла в памяти
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Users list");

        // Counter for lines
        int rowNum = 0;

        // Create 0-th row (the columns names)
        Row row = sheet.createRow(rowNum);

        row.createCell(0).setCellValue("Id");
        row.createCell(1).setCellValue("Name");
        row.createCell(2).setCellValue("Username");
        row.createCell(3).setCellValue("Email");
        row.createCell(4).setCellValue("RegisteredDate");

        //Fill all lines for exel result table
        rowNum = 1;
        for (ClientsAddressBook clients : allShopUsersList) {
            Row row1 = sheet.createRow(rowNum);

            row1.createCell(0).setCellValue(clients.clientID);
            row1.createCell(1).setCellValue(clients.clientName);
            row1.createCell(2).setCellValue(clients.clientUserName);
            row1.createCell(3).setCellValue(clients.clientEmail);
            row1.createCell(4).setCellValue(clients.accountCreatedData);

            rowNum++;
        }

        // Safe extracted data to xls file
        try (FileOutputStream out = new FileOutputStream(new File(extractedDataFolder + extractedDataExcelFileName))) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Excel file is created!");

    }

    public static void main(String[] args) throws SQLException {
        System.out.println("Connecting to DB");
//
//        List<ClientsAddressBook> list = new ArrayList<>();
//        ClientsAddressBook client1 = new ClientsAddressBook (1, "emai1@mail.ru", "Client1");
//        ClientsAddressBook client2 = new ClientsAddressBook (2, "emai2@mail.ru", "Client2");
//        ClientsAddressBook client3 = new ClientsAddressBook (3, "emai3@mail.ru", "Client3");
//        list.add(client1);
//        list.add(client2);
//        list.add(client3);
//
//        System.out.println(list);
//
        //Extract users from Joomla/Virtuemart shop
        connectToDBUsersList(JoonlaAccesCredentionals.shopDBUrl, JoonlaAccesCredentionals.shopDBAdminLogin,
                JoonlaAccesCredentionals.shopDBAdminPassword);
        safeUsersListToExcelFile();
    }
}
