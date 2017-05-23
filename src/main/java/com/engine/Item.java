package com.engine;

import com.engine.product.Product;

import java.math.BigDecimal;

/**
 * @author Krzysztof Ziomek
 * @since 23/05/2017.
 */
public class Item {

    public String name;
    public BigDecimal servicePrice;
    public BigDecimal componentsPrice;
    public BigDecimal discountedPrice;

    public Item(Product product) {
        name = product.getServiceName();
        servicePrice = product.getServicePrice();
        discountedPrice = product.getServicePrice();
        componentsPrice = product.getComponentsPrice();
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public String getName() {
        return name;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public BigDecimal getCatalogPrice() {
        return servicePrice;
    }

    public BigDecimal getTotalPrice() {
        return discountedPrice.add(componentsPrice);

    }
}
