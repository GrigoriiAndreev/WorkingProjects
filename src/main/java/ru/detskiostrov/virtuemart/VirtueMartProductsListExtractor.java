package ru.detskiostrov.virtuemart;

/**
 * This class extracting VirtueMart product list
 * Author: Grigorii Andreev
 * Date: 22 March 2021
 */
public class VirtueMartProductsListExtractor {

    // Be careful to store secure and private data
    private static final String shopDBAdminLogin = "u1029682_integ2";
    private static final String shopDBAdminPassword = "123qweQWE";
    private static final String shopDBIP = "5.253.60.105";
    private static final String shopDBName = "db1029682_integ2";
    private static final String dbTableName = "orszw_users";
    private static final String extractedDataFolder = "D:/JavaStudy/UsefullUtils/src/main/resources/resultfolder/";
    private static final String extractedDataExcelFileName = "db_" + shopDBName + "users_list.xls";
    private static final String dbSQLQueryToGetUsersList = "select * from orszw_users";
    private static final String shopDBUrl = "jdbc:mysql://" + shopDBIP + ":3306/" + shopDBName;


}
