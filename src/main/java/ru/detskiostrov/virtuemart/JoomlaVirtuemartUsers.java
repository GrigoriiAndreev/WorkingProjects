/*
 *  Joomla MySQL db users list table structure
 *  Author: Grigorii Andreev
 *  Date: 25 March 2021
 */

package ru.detskiostrov.virtuemart;

public class JoomlaVirtuemartUsers {

    int id;
    String name;
    String username;
    String email;
    String password;
    String usertype;
    byte block;
    byte sendEmail;
    byte registerDate;
    byte lastvisitDate;
    String activation;
    String params;
    String lastResetTime;
    int resetCount;
}
