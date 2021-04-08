/*
 *  Description
 *  Author: Grigorii Andreev
 *  Date: 29.03.2021
 */

package ru.detskiostrov.virtuemart;

public class JoonlaAccesCredentionals {

    // Be careful to store secure and private data
    public static final String shopDBAdminLogin = "u1029682_integral";
    public static final String shopDBAdminPassword = "123qweQWE";
    public static final String shopDBIP = "5.253.60.105";
    public static final String shopDBName = "db1029682_copy";
    public static final String dbTableName = "orszw_users";
    static final String extractedDataFolder = "D:/JavaStudy/UsefullUtils/src/main/resources/resultfolder/";
//    static final String extractedDataExcelFileName = "db_" + shopDBName + "users_list.xls";
//    static final String dbSQLQueryToGetUsersList = "select * from orszw_users";
    public static final String shopDBUrl = "jdbc:mysql://" + shopDBIP + ":3306/" + shopDBName;

}
