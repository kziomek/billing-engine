package com.engine.rules;


import com.engine.Item;
import com.engine.facts.AgeFact;
import com.engine.facts.Fact;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

/**
 * @author Krzysztof Ziomek
 * @since 23/05/2017.
 */
public class AgeRangeRule implements Rule {

    private Integer lowerBound;
    private Integer upperBound;

    private BigDecimal discountPerc;


    public AgeRangeRule(Integer lowerBound, Integer upperBound, BigDecimal discountPerc) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.discountPerc = discountPerc;
    }

    @Override
    public boolean isAddition() {
        return false;
    }

    @Override
    public boolean isApplying(Item Item, Set<Fact> facts) {
        Optional<Fact> ageFactOptional = facts.stream().filter(fact -> fact instanceof AgeFact).findFirst();
        if (ageFactOptional.isPresent()) {
            AgeFact fact = (AgeFact) ageFactOptional.get();
            if (fact.getAge().compareTo(lowerBound) >= 0 && fact.getAge().compareTo(upperBound) <= 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void apply(Item item, Set<Fact> facts) {
        if (!isApplying(item, facts)) {
            throw new IllegalStateException("AgeRangeRule is not applying to given facts.");
        }
        item.setDiscountedPrice(item.getDiscountedPrice().subtract(item.getCatalogPrice().multiply(discountPerc)).setScale(2, ROUND_HALF_DOWN));
    }

}
