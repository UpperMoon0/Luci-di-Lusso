package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.entity.Delivery;

import java.util.List;

public interface IDeliveryService {

    public List<Delivery> getDeliveriesByUser(Long delivererID);

    public void completeDelivery(Long deliveryID);

    public void createDelivery(Long orderID);

    public void assignDeliverer(Long deliveryID, Long deliverer);

    List<Delivery> getUnassignedDeliveries();

}
