package de.edvschuleplattling.rjertila.parkautomat.viewclasses;

import javafx.scene.control.Label;

/**
 * This class is used to display the total amount of money in the GUI
 * @author ploebl
 */
public class BetragLabel extends Label {
    public BetragLabel() {
        super();
    }

    public String getTextValue() {
        return this.getText();
    }

    public void setTextValue(String textValue) {
        this.setText(textValue);
    }

    public void updateTextValue() {
        String formattedValue = String.format("%05.2f", value);
        this.setText(formattedValue + " €");
    }

    private double value;

    public int getValue() {
        return (int) (value * 100);
    }

    public void setValue(double value) {
        this.value = value;
        updateTextValue();
    }
}
