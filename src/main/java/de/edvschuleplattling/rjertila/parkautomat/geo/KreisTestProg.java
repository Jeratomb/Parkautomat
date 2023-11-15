package de.edvschuleplattling.rjertila.parkautomat.geo;

public class KreisTestProg {
    public static void main(String[] args) {
        Kreis k1 = new Kreis(2);
        Kreis k2 = new Kreis(2.1);
        Rechteck rk = new Rechteck(2,4);
        GeoForm[] figuren = { k1, k2, new Kreis(), new Kreis(k1),new Rechteck(2.5,5),new Quadrat(8.5),new Dreieck(2,4,6),new Eclipse(2,4)  };
        k1.setRadius(42);
        k2.setFlaeche(10);

        System.out.println("Maße");
        for (int i = 0; i<figuren.length; i++) {
            System.out.printf("%2d %s %8.2f %8.2f\n",i,figuren[i].toString(), figuren[i].berechneUmfang(), figuren[i].berechneFlaeche());
        }
        System.out.println();

        System.out.println("Vergleich mit k2");
        for (int i = 0; i<figuren.length; i++) {
            System.out.printf("%2d %s als k2\n",i,figuren[i].groesser(k2)?"Größer":"Kleiner gleich");
        }
    }
}
