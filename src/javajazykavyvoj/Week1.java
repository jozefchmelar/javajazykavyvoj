/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javajazykavyvoj;

import javajazykavyvoj.Util;
import java.math.*;
import java.util.LinkedList;

/**
 *
 * @author jozef.chmelar.ml
 */
public class Week1 {

    /**
     * Načítať celé číslo >0, vypísať najbližšie väčšie prvočíslo.
     *
     * @param beginning number >0
     */
    public static int nearestPrime(int beginning) {
        if (beginning > 0) {
            if (!Util.isPrime(beginning)) {
                while (!Util.isPrime(++beginning));
            }
            return beginning;
        }
        return -1;
    }

    /**
     * Vypocitat sinus 2. Vypocitajte sinus X s pouzitim zakladnych operacii
     * (+,-,*,/). Vypocet vykonajte pre zvolenu presnost EPSILON podla
     * Taylorovho rozvoja: Sin X = +X1/1! – X3/3! + X5/5! – X7/7! +
     *
     * @param x number to calculate sin from
     * @param epsilon perfection.
     * @return
     */
    public static double sin(final double x, final int epsilon) {
        double result = x;
        int pow = 3;
        for (int i = 3; i <= epsilon; i += 2) {
            result += Math.pow(-1, pow++) * (i * x / Util.factorial(i));
        }
        return result;
    }
    //

    /**
     * 3. V skole je n (napr. n=100) skriniek na veci pre studentov. Na zaciatku
     * su vsetky skrinky otvorene. Pride skolnik a pre kazdu druhu skrinku v
     * poradi urobi nasledujucu operaciu: ak je skrinka otvorena tak ju zatvori,
     * ak je zatvorena, tak ju otvori. Ked pride na koniec radu skriniek, vrati
     * sa na zaciatok a zacne opat od zaciatku vykonavat tu istu operaciu, ale
     * teraz pre kazdu tretiu skrinku. No a takto to pokracuje, skolnik sa vzdy
     * vrati na zaciatok a robi definovanu operaciu pre kazdu k-tu skrinku od
     * zaciatku, pricom k sa zvacsuje od 2 po n. //Otazka znie: ktore skrinky
     * budu po tomto procese otvorene a preco? (rieste pre n=100)
     *
     * @param n number of lockers
     * @return
     */
    public static int numberOfLockers(int n) {
        class Locker {

            private boolean isOpen;
            
            public Locker() {
                this.isOpen = true;
            }

            public boolean visit() {
                this.isOpen = !this.isOpen;
                return this.isOpen;
            }
        }

        LinkedList<Locker> lockers = new LinkedList<>();
        int openedLockers = n;
        for (int i = 0; i < n; i++) {
            lockers.add(new Locker());
        }
        
        for (int k = 2; k < lockers.size(); k++) {
            for (int i = 0; i < lockers.size(); i++) {
                if (i % k == 0) {
                    if(lockers.get(i).visit())
                        openedLockers++;
                    else openedLockers--;
                }
            }
        }
        return openedLockers;
    }
}
