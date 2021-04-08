package ru.detskiostrov.virtuemart;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class contain the orders for Virtuemart. Methods extract data from My SQL DB ans safe them to Excel file
 * Author: Grigorii Andreev
 * Data: 12 March 2021
 */

public class VirtuemartOrderList {

    //Data to connect to Virtuemart shops
    private static final String shopDBAdminLogin = JoonlaAccesCredentionals.shopDBAdminLogin;
    private static final String shopDBAdminPassword = JoonlaAccesCredentionals.shopDBAdminPassword;
    private static final String shopDBIP = JoonlaAccesCredentionals.shopDBIP;
    private static final String extractedDataFolder = JoonlaAccesCredentionals.extractedDataFolder;
    private static final String extractedDataExcelFileName = "db_" + JoonlaAccesCredentionals.shopDBName
            + "_orders_list.xls";
    static final String shopDBUrl = "jdbc:mysql://" + shopDBIP + ":3306/" + JoonlaAccesCredentionals.shopDBName;

    private static final String dbSQLQueryToGetOrdersList =
            "SELECT dqope_virtuemart_order_items.virtuemart_order_id, dqope_virtuemart_orders.order_number, dqope_virtuemart_order_items.order_item_name, dqope_virtuemart_order_items.virtuemart_product_id, dqope_virtuemart_order_items.order_item_sku, dqope_virtuemart_order_items.product_item_price, dqope_virtuemart_order_items.created_on, dqope_virtuemart_order_items.order_status, dqope_virtuemart_product_manufacturers.virtuemart_manufacturer_id\n" +
                    "FROM dqope_virtuemart_order_items, dqope_virtuemart_orders, dqope_virtuemart_product_manufacturers\n" +
                    "WHERE dqope_virtuemart_orders.virtuemart_order_id = dqope_virtuemart_order_items.virtuemart_order_id\n" +
                    "AND dqope_virtuemart_orders.created_on BETWEEN '2021-01-01' AND '2021-04-01' AND dqope_virtuemart_orders.order_status = 'C' AND dqope_virtuemart_product_manufacturers.virtuemart_product_id = dqope_virtuemart_order_items.virtuemart_product_id\n ";

    static List<VirtuemartOrderItems> virtuemartOrderList = new ArrayList();
    static VirtuemartOrderItems virtuemartSingleOrder;

    public static void connectToDBOrdersList(String shopDBUrl, String shopDBAdminLogin, String shopDBAdminPassword) throws SQLException {

        //Establish the connection
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        try (Connection connection = DriverManager.getConnection(shopDBUrl, shopDBAdminLogin, shopDBAdminPassword);
             Statement statement = connection.createStatement()) {
            System.out.println("The connection to DB is established!");
            ResultSet resultSet = statement.executeQuery(dbSQLQueryToGetOrdersList);

            while (resultSet.next()) {
                virtuemartSingleOrder = new VirtuemartOrderItems();

                virtuemartSingleOrder.setVirtuemartOrderId(resultSet.getInt(1));
                virtuemartSingleOrder.setOrderNumber(resultSet.getString(2));
                virtuemartSingleOrder.setOrderItemName(resultSet.getString(3));
                virtuemartSingleOrder.setVirtuemartProductId(resultSet.getInt(4));
                virtuemartSingleOrder.setOrderItemSku(resultSet.getString(5));
                virtuemartSingleOrder.setProductItemPrice(resultSet.getInt(6));
                virtuemartSingleOrder.setCreatedOn(resultSet.getString(7));
                virtuemartSingleOrder.setOrderStatus(resultSet.getString(8));
                virtuemartSingleOrder.setVirtuemartManufacturerId(resultSet.getInt(9));

                virtuemartOrderList.add(virtuemartSingleOrder);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    // Safe etracted data ro Excel file
    public static void safeVirtuemartOrderListToExcelFile() {

        // Create Excel workbook variable
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Orders list");

        // Counter for rows
        int rowNum = 0;

        // Create 0-th row (the columns names)
        Row row = sheet.createRow(rowNum);

        row.createCell(0).setCellValue("virtuemartOrderId");
        row.createCell(1).setCellValue("orderNumber");
        row.createCell(2).setCellValue("orderItemName");
        row.createCell(3).setCellValue("virtuemartProductId");
        row.createCell(4).setCellValue("orderItemSku");
        row.createCell(5).setCellValue("productItemPrice");
        row.createCell(6).setCellValue("createdOn");
        row.createCell(7).setCellValue("orderStatus");
        row.createCell(8).setCellValue("virtuemartManufacturerId");

        //Fill all lines for exel result table
        rowNum = 1;
        for (VirtuemartOrderItems clients : virtuemartOrderList) {
            Row row1 = sheet.createRow(rowNum);

            row1.createCell(0).setCellValue(clients.getVirtuemartOrderId());
            row1.createCell(1).setCellValue(clients.getOrderNumber());
            row1.createCell(2).setCellValue(clients.getOrderItemName());
            row1.createCell(3).setCellValue(clients.getVirtuemartProductId());
            row1.createCell(4).setCellValue(clients.getOrderItemSku());
            row1.createCell(5).setCellValue(clients.getProductItemPrice());
            row1.createCell(6).setCellValue(clients.getCreatedOn());
            row1.createCell(7).setCellValue(clients.getOrderStatus());
            row1.createCell(8).setCellValue(clients.getVirtuemartManufacturerId());

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

        //Extract users from Joomla/Virtuemart shop
        connectToDBOrdersList(shopDBUrl, shopDBAdminLogin, shopDBAdminPassword);
        safeVirtuemartOrderListToExcelFile();

    }


}
