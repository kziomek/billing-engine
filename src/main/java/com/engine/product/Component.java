package com.engine.product;

import java.math.BigDecimal;

/**
 * @author Krzysztof Ziomek
 * @since 24/05/2017.
 */
public class Component {

    private String name;
    private BigDecimal number;
    private BigDecimal price;

    public Component(String name, BigDecimal number, BigDecimal price) {
        this.name = name;
        this.number = number;
        this.price = price;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
