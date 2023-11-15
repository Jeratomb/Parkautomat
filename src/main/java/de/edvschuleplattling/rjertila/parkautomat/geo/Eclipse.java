package de.edvschuleplattling.rjertila.parkautomat.geo;

public class Eclipse extends GeoForm{
    private double radius1;
    private double radius2;

    public Eclipse(double radius1, double radius2) {
       setRadius1(radius1);
       setRadius2(radius2);
    }

    public double getRadius1() {
        return radius1;
    }

    public void setRadius1(double radius1) {
        pruefeZahl(radius1);
        this.radius1 = radius1;
    }

    public double getRadius2() {
        return radius2;
    }

    public void setRadius2(double radius2) {
        pruefeZahl(radius2);
        this.radius2 = radius2;
    }

    public double berechneFlaeche(){
        return radius1 * radius2 * Math.PI;
    }
    public double berechneKomischesZeichen(){
        return (radius1 - radius2)/(radius1 + radius2);
    }

    public double berechneUmfang(){
        double kZ = berechneKomischesZeichen();
        return Math.PI * (radius1 + radius2) * (1 + (3*kZ*kZ)/(10+Math.sqrt(4-3*kZ*kZ)));
    }

    @Override
    public String toString() {
        return "Eclipse{" +
                "radius1=" + getRadius1() +
                ", radius2=" + getRadius2() +
                '}';
    }
}
