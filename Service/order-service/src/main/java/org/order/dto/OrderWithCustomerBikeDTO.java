package org.order.dto;

import org.order.entity.Order;

public class OrderWithCustomerBikeDTO {
    private Order order;
    private ExternalCustomerDTO customer;
    private ExternalBikeDTO bike;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ExternalCustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(ExternalCustomerDTO customer) {
        this.customer = customer;
    }

    public ExternalBikeDTO getBike() {
        return bike;
    }

    public void setBike(ExternalBikeDTO bike) {
        this.bike = bike;
    }
}
