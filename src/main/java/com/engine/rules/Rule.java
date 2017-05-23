package com.engine.rules;

import com.engine.Item;
import com.engine.facts.Fact;

import java.util.Set;

/**
 * @author Krzysztof Ziomek
 * @since 23/05/2017.
 */
public interface Rule {
    
    boolean isAddition();

    boolean isApplying(Item item, Set<Fact> facts);

    void apply(Item item, Set<Fact> facts);

}
