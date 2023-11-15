package de.edvschuleplattling.rjertila.parkautomat.elektrogeraete;

public class TestProg {
    public static void main(String[] args) {
        Bohrmaschine b1 = new Bohrmaschine(1200, 2700);
        Bohrmaschine b2 = new Bohrmaschine(1900, 4200);
        ElektroMeissel em1 = new ElektroMeissel(1800,48.5);
        ElektroMeissel em2 = new ElektroMeissel(2900,90);
        Staubsauger sg1 = new Staubsauger(1100,40);
        Akkuschrauber as = new Akkuschrauber(1100,9);

        /*E1*/	ElektroGeraet geraete[] = { em1, b1, em2,sg1,as};
        /*E2*/	// Bohrmaschine ba1[] = { em1, b1, b2, em2};
        /*E3*/	Bohrmaschine ba2[] = { b1, b2};

        for (int i = 0; i < geraete.length; i++) {
            /*E4*/			System.out.println(geraete[i].getLeistung());
            /*E5*/			System.out.println(geraete[i].willStarkenStrom());
            /*E6*/			//System.out.println(geraete[i].getUmin());
            /*E7*/			System.out.println(geraete[i].toString());
            /*E8*/			System.out.println(geraete[i].equals(b1));
            /*E9*/			System.out.println(geraete[i].getSound());
            /*E10*/			geraete[i].doSound();
            System.out.println("-------------------");
        }
    }
}
