package ru.detskiostrov.webservices;

/**
 * This parser is used to scan all urls of site and safe it xls file
 * There is the option to create the sitemap in xml format
 * Author: Grigorii Andreev
 * Creation date: 13 March 2021
 * Version: 1.00
 */
public class WebSiteParser {

    String sourceWebSiteUrl = "";
    String workingFolder = "";                     // Local folder to store all created files;
    Boolean fileListInXMLRequired = true;          // false - NOT generate the xml sitemap;
    Boolean fileListInPlainFormat = true;
    Boolean foldersList = true;                     // false - NOT created separate folders list;


}
