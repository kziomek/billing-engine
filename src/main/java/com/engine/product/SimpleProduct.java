package com.engine.product;

import java.math.BigDecimal;

/**
 * @author Krzysztof Ziomek
 * @since 23/05/2017.
 */
public class SimpleProduct extends Product {

    private String name;
    private BigDecimal price;

    public SimpleProduct(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getServiceName() {
        return name;
    }

    @Override
    public BigDecimal getComponentsPrice() {
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getServicePrice(){
        return price;
    }

}
