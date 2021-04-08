/*
 *  class is created to study debugging
 *  Author: Grigorii Andreev
 *  Date: 06 April 2021
 */

package ru.detskiostrov.webservices;

import static java.lang.Math.sqrt;

public class ClassForStudyIDEADebugging {
    public static void main(String[] args) {

        int number;
        number = 100;
        float floatNumber;
        floatNumber = 3.14F;

        for (int i = 1; i < 10; i++) {
            System.out.println(i + " " + sqrt(i * floatNumber));
        }
    }

}
