package com.engine.product;

import java.math.BigDecimal;

/**
 * @author Krzysztof Ziomek
 * @since 24/05/2017.
 */
public class ComponentProduct extends Product {

    private String serviceName;
    private BigDecimal servicePrice;

    private BigDecimal componentNumber;
    private BigDecimal componentPrice;


    public ComponentProduct(String serviceName, BigDecimal servicePrice, BigDecimal componentNumber, BigDecimal componentPrice) {
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
        this.componentNumber = componentNumber;
        this.componentPrice = componentPrice;
    }

    @Override
    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public BigDecimal getComponentsPrice() {
        return componentNumber.multiply(componentPrice);
    }

}
