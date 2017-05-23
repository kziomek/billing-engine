package com.engine.product;

import java.math.BigDecimal;

/**
 * @author Krzysztof Ziomek
 * @since 24/05/2017.
 */
public abstract  class Product {

    public abstract BigDecimal getServicePrice();

    public abstract String getServiceName();

    public abstract BigDecimal getComponentsPrice();

}
