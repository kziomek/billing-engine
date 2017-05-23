package com.engine.rules;

import com.engine.Item;
import com.engine.facts.Fact;
import com.engine.facts.InsuranceFact;
import com.engine.facts.PractitionerFact;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

/**
 * @author Krzysztof Ziomek
 * @since 23/05/2017.
 */
public class InsuranceRule implements Rule {

    private String insuranceName;
    private String practitionerGroup;
    private String productName;
    private BigDecimal discountPerc;

    public InsuranceRule(String insuranceName, String practitionerGroup, String productName, BigDecimal discountPerc) {
        this.insuranceName = insuranceName;
        this.practitionerGroup = practitionerGroup;
        this.productName = productName;
        this.discountPerc = discountPerc;
    }

    @Override
    public boolean isAddition() {
        return true;
    }

    @Override
    public boolean isApplying(Item item, Set<Fact> facts) {
        Optional<Fact> insuranceFactOptional = facts.stream().filter(fact -> fact instanceof InsuranceFact).findFirst();
        Optional<Fact> practitionerFactOptional = facts.stream().filter(fact -> fact instanceof PractitionerFact).findFirst();

        if (insuranceFactOptional.isPresent() && practitionerFactOptional.isPresent()) {
            if (insuranceName.equals(((InsuranceFact) insuranceFactOptional.get()).getInsurance())
                    && practitionerGroup.equals(((PractitionerFact) practitionerFactOptional.get()).getBrand())
                    && productName.equals(item.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void apply(Item item, Set<Fact> facts) {
        if (!isApplying(item, facts)) {
            throw new IllegalStateException("InsuranceRule is not applying to given facts.");
        }
        item.setDiscountedPrice(item.getDiscountedPrice().subtract(item.getCatalogPrice().multiply(discountPerc)).setScale(2, ROUND_HALF_DOWN));

    }
}
