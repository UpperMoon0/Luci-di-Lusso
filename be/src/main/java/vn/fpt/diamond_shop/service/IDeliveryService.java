package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.entity.Delivery;

import java.util.List;

public interface IDeliveryService {

    public List<Delivery> getDeliveriesByUser(Long delivererID);

    public void completeDelivery(Long deliveryID);

}
