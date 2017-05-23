package com.engine.facts;

/**
 * @author Krzysztof Ziomek
 * @since 23/05/2017.
 */
public class PractitionerFact implements Fact {

    private String brand;

    public PractitionerFact(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }
}
