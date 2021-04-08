package ru.detskiostrov.addresses;

import java.util.ArrayList;

/**
 * This class used to store personal contacts, getting from google contacts and smartphone
 * Author: Grigorii Andreev
 * Version: 1.0
 * Data: 11 March 2021
 */
public class MyPrivateAddressBook {

    static final String sourceFileUrl = "";     //Read .cvs file with . Exported from Yandex mail address book
    static final String sourceDirectory = "";

    private int id;
    private String firstName;
    private String secondName;
    private String middleName;      //Отчество, если есть
    private int age;
    private String telephone;
    private String email;

    //Store all data in one arraylist
    ArrayList<MyPrivateAddressBook> myPrivateAddressBook = new ArrayList();

    public MyPrivateAddressBook(int id, String firstName, String secondName, String middleName, int age, String telephone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.age = age;
        this.telephone = telephone;
        this.email = email;
    }

    //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static void main(String[] args) {

    }

    public void readDataFromFile(){

    }

    public void readDataFromDB(){

    }

    public void safeAllContactsToFile() {

    }

    public void safeAllContactsToDB() {

    }

}
