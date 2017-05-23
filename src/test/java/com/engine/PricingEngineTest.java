package com.engine;

import com.engine.facts.AgeFact;
import com.engine.facts.Fact;
import com.engine.facts.InsuranceFact;
import com.engine.facts.PractitionerFact;
import com.engine.product.ComponentProduct;
import com.engine.product.SimpleProduct;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Krzysztof Ziomek
 * @since 23/05/2017.
 */
public class PricingEngineTest {


    @Test
    public void calculateShouldDiscount60PercentForDiagnosisForCitizensAgedBetween65And70() {
        SimpleProduct diagnosis = new SimpleProduct("Diagnosis", new BigDecimal("60"));
        Set<Fact> facts = new HashSet<>();
        facts.add(new AgeFact(65));

        Item item = new PricingEngine().calculate(diagnosis, facts);

        Assert.assertEquals(new BigDecimal("24.00"), item.getDiscountedPrice());
    }

    @Test
    public void calculateShouldntDiscountForDiagnosisForCitizensAged50() {
        SimpleProduct diagnosis = new SimpleProduct("Diagnosis", new BigDecimal("60"));
        Set<Fact> facts = new HashSet<>();
        facts.add(new AgeFact(50));

        Item item = new PricingEngine().calculate(diagnosis, facts);

        Assert.assertEquals(new BigDecimal("60"), item.getDiscountedPrice());
    }

    @Test
    public void calculateShouldDiscount90PercentForXRayWhenCitizenOlderThan70() {
        SimpleProduct xray = new SimpleProduct("X-Ray", new BigDecimal("150"));
        Set<Fact> facts = new HashSet<>();
        facts.add(new AgeFact(75));

        Item item = new PricingEngine().calculate(xray, facts);

        Assert.assertEquals(new BigDecimal("15.00"), item.getDiscountedPrice());
    }

    @Test
    public void calculateShouldDiscount40PercentForBloodTestWhenChildYoungerThan5() {
        SimpleProduct bloodTest = new SimpleProduct("Blood Test", new BigDecimal("78"));
        Set<Fact> facts = new HashSet<>();
        facts.add(new AgeFact(4));

        Item item = new PricingEngine().calculate(bloodTest, facts);

        Assert.assertEquals(new BigDecimal("46.80"), item.getDiscountedPrice());
    }

    @Test
    public void calculateShouldApplyAdditional15PercentDiscountOnBloodTestWhenDiagnosedByMediHealth() {
        SimpleProduct bloodTest = new SimpleProduct("Blood Test", new BigDecimal("78"));
        Set<Fact> facts = new HashSet<>();
        facts.add(new AgeFact(4));
        facts.add(new PractitionerFact("MediHealth"));
        facts.add(new InsuranceFact("MediHealth Health insurance"));

        Item item = new PricingEngine().calculate(bloodTest, facts);

        Assert.assertEquals(new BigDecimal("35.10"), item.getDiscountedPrice());

    }

    @Test
    public void calculateShouldNotApplyAdditional15PercentWithoutMediHealthInsurance() {
        SimpleProduct bloodTest = new SimpleProduct("Blood Test", new BigDecimal("78"));
        Set<Fact> facts = new HashSet<>();
        facts.add(new AgeFact(25));
        facts.add(new PractitionerFact("MediHealth"));
        facts.add(new InsuranceFact("Other insurance"));

        Item item = new PricingEngine().calculate(bloodTest, facts);

        Assert.assertEquals(new BigDecimal("78"), item.getDiscountedPrice());

    }

    @Test
    public void calculateShouldNotApplyAdditional15PercentForXRay() {
        SimpleProduct bloodTest = new SimpleProduct("X-Ray", new BigDecimal("150"));
        Set<Fact> facts = new HashSet<>();
        facts.add(new AgeFact(50));
        facts.add(new PractitionerFact("MediHealth"));
        facts.add(new InsuranceFact("MediHealth Health insurance"));

        Item item = new PricingEngine().calculate(bloodTest, facts);

        Assert.assertEquals(new BigDecimal("150"), item.getDiscountedPrice());

    }

    @Test
    public void calculateShouldDiscountVaccineServiceButNotVaccines() {
        ComponentProduct vaccine = new ComponentProduct("Vaccine", new BigDecimal("27.50"), new BigDecimal("2"), new BigDecimal("15.00"));
        Set<Fact> facts = new HashSet<>();
        facts.add(new AgeFact(80));

        Item item = new PricingEngine().calculate(vaccine, facts);

        Assert.assertEquals(new BigDecimal("32.75"), item.getTotalPrice());

    }


}
