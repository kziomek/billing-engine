package com.engine.product;

import java.math.BigDecimal;

/**
 * @author Krzysztof Ziomek
 * @since 24/05/2017.
 */
public class ComponentProduct extends Product {

    private String serviceName;
    private BigDecimal servicePrice;

    private Component component;


    public ComponentProduct(String serviceName, BigDecimal servicePrice, String componentName, BigDecimal componentNumber, BigDecimal componentPrice) {
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
        component = new Component(componentName, componentNumber, componentPrice);
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
        return component.getNumber().multiply(component.getPrice());
    }

}
