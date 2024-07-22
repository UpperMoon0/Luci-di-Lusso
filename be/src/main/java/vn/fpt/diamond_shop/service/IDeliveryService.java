package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.entity.Delivery;

import java.util.List;

public interface IDeliveryService {

    List<Delivery> getDeliveriesByAccount(Long delivererID);

    void completeDelivery(Long deliveryID);

    void createDelivery(Long orderID);

    void assignDeliverer(Long deliveryID, Long deliverer);

    List<Delivery> getUnassignedDeliveries();

}
