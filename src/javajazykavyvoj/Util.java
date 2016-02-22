/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javajazykavyvoj;

/**
 *
 * @author jozef.chmelar.ml
 */
public class Util {

    public static boolean isPrime(int number) {
        if (number % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int factorial(int number) {
        if (number <= 1) {
            return 1;
        }
        return factorial(number - 1) * number;
    }

    public static String removeSpaces(String string) {
        return string.replaceAll("\\s", "");
    }

    public static String reverse(String string) { 
        StringBuilder sb = new StringBuilder(string);
        return sb.reverse().toString();
    }
}
