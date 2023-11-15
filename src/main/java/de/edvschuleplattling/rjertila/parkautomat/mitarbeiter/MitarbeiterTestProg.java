package de.edvschuleplattling.rjertila.parkautomat.mitarbeiter;

import java.time.LocalDate;

public class MitarbeiterTestProg {
    public static void main(String[] args) {
        Mitarbeiter hans = new Angestellter(10001, LocalDate.of(1972,6,12),4);
        Mitarbeiter pascal = new Angestellter(20002,LocalDate.of(1984,10,2),2);
        Mitarbeiter christian = new Angestellter(20003,LocalDate.of(1982,10,29),3);
        Mitarbeiter harald = new Angestellter(10002,LocalDate.of(1957,9,12),6);
        Mitarbeiter daniel = new Lehrling(10004,LocalDate.of(2002,2,9),2);
        Mitarbeiter rayen = new Lehrling(10004,LocalDate.of(2002,7,9),2);
        Mitarbeiter phillip = new Lehrling(10004,LocalDate.of(2004,11,27),1);
        Mitarbeiter roth = new Angestellter(10004,LocalDate.of(1995,7,21),5);
        Mitarbeiter graf = new Lehrling(10004,LocalDate.of(2000,9,9),4);
        Mitarbeiter andreas = new Lehrling(10004,LocalDate.of(2001,8,21),3);

        hans.setNummer(10004);
        System.out.println(hans.getNummer());

        /*
        pascal.setGehaltsStufe(3);
        System.out.println(pascal.getGehaltsStufe());

         */

        System.out.println("Hans: "+hans.toString());
        System.out.println("Christian: "+christian);

        System.out.println(hans.getGehalt());
        System.out.println(hans.getGehalt(2022,6));

        System.out.println(hans.kleiner(christian));
        System.out.println(hans.equals(christian));
        System.out.println(hans.groesser(christian));

        // Fängt die Exception ab
        try {
            Mitarbeiter kyle = new Angestellter(30001,LocalDate.of(1992,8,14),0);
        } catch (Throwable e) { // Statt Throwbale kann hier auch RuntimeException stehen
            System.out.println("!!! Da passt was nicht: "+e);
        }
        // Danach geht es ordnungsgemaess weiter

        Mitarbeiter[] mitarbeiter = {hans, pascal, christian, harald};

        System.out.println("Gehälter Oktober 2022");
        for (Mitarbeiter m: mitarbeiter  ) {
            System.out.println(m.getNummer()+": "+m.getGehalt(2022,10));
        }
        System.out.println();

        System.out.println("Gehälter September 2022");
        for (Mitarbeiter m: mitarbeiter  ) {
            System.out.println(m.getNummer()+": "+m.getGehalt(2022,9));
        }

        Mitarbeiter[] mitarbeiterTest = {hans,pascal,christian,harald,daniel,rayen,phillip,roth,graf,andreas};







    }
}
