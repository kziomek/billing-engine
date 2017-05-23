package com.engine.facts;

/**
 * @author Krzysztof Ziomek
 * @since 23/05/2017.
 */
public class InsuranceFact implements Fact {

    private String insurance;

    public InsuranceFact(String insurance){
        this.insurance = insurance;
    }

    public String getInsurance() {
        return insurance;
    }
}
