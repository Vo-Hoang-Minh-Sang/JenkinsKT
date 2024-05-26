package org.example.deliver.dto;

import org.example.deliver.entity.Deliver;

public class DeliverWithOrderCustomerDTO {
    private Deliver deliver;
    private ExternalOrderDTO order;
    private ExternalCustomerDTO customer;

    public Deliver getDeliver() {
        return deliver;
    }

    public void setDeliver(Deliver deliver) {
        this.deliver = deliver;
    }

    public ExternalOrderDTO getOrder() {
        return order;
    }

    public void setOrder(ExternalOrderDTO order) {
        this.order = order;
    }

    public ExternalCustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(ExternalCustomerDTO customer) {
        this.customer = customer;
    }
}
