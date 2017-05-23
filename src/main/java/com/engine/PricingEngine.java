package com.engine;

import com.engine.facts.Fact;
import com.engine.product.Product;
import com.engine.rules.AgeRangeRule;
import com.engine.rules.InsuranceRule;
import com.engine.rules.Rule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Krzysztof Ziomek
 * @since 23/05/2017.
 */
public class PricingEngine {

    private List<Rule> rules;

    public PricingEngine() {
        rules = new ArrayList<>();
        rules.add(new AgeRangeRule(65, 70, new BigDecimal("0.6")));
        rules.add(new AgeRangeRule(71, 200, new BigDecimal("0.9")));
        rules.add(new AgeRangeRule(0, 4, new BigDecimal("0.4")));
        rules.add(new InsuranceRule("MediHealth Health insurance", "MediHealth", "Blood Test", new BigDecimal("0.15")));
    }

    public Item calculate(Product product, Set<Fact> facts) {
        Item item = new Item(product);

        Optional<Rule> mainRule = findMainRule(item, facts);

        if (mainRule.isPresent()) {
            mainRule.get().apply(item, facts);
        }

        Optional<Rule> additionalRule = findAdditionalRule(item, facts);
        if (additionalRule.isPresent()) {
            additionalRule.get().apply(item, facts);
        }

        return item;
    }

    private Optional<Rule> findMainRule(Item item, Set<Fact> facts) {
        return rules.stream()
                .filter((rule) -> !rule.isAddition())
                .filter(rule -> rule.isApplying(item, facts))
                .findFirst();
    }

    private Optional<Rule> findAdditionalRule(Item item, Set<Fact> facts) {
        return rules.stream()
                .filter(Rule::isAddition)
                .filter(rule -> rule.isApplying(item, facts))
                .findFirst();
    }


}
