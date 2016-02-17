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
 
 public static boolean isPrime(int num) {
        if (num % 2 == 0)
            return false;
        for (int i = 3; i * i <= num; i += 2)
            if (num % i == 0) return false;
        return true;
  }  
 
 public static int factorial(int num){
     if(num<=1)
         return 1;       
     return factorial(num-1) * num;           
 }

}
