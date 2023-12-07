package de.edvschuleplattling.rjertila.parkautomat.viewclasses;

import javafx.scene.control.Label;

/**
 * Diese Klasse wird verwendet, um den Gesamtbetrag des Geldes in der GUI anzuzeigen.
 * @author rjertila
 */
public class BetragLabel extends Label {
    public BetragLabel() {
        super();
    }

    /**
     * Gibt den Textwert des Labels zurück.
     * @return Der Textwert des Labels.
     */
    public String getTextValue() {
        return this.getText();
    }

    /**
     * Setzt den Textwert des Labels.
     * @param textValue Der neue Textwert des Labels.
     */
    public void setTextValue(String textValue) {
        this.setText(textValue);
    }

    /**
     * Aktualisiert den Textwert des Labels.
     */
    public void updateTextValue() {
        String formattedValue = String.format("%05.2f", value);
        this.setText(formattedValue + " €");
    }

    private double value;

    /**
     * Gibt den Wert des Labels zurück.
     * @return Der Wert des Labels.
     */
    public int getValue() {
        return (int) (value * 100);
    }

    /**
     * Setzt den Wert des Labels.
     * @param value Der neue Wert des Labels.
     */
    public void setValue(double value) {
        this.value = value;
        updateTextValue();
    }
}