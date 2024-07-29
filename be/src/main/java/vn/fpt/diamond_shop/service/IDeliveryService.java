package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.entity.Delivery;

import java.util.List;

public interface IDeliveryService {

    List<Delivery> findByAccount(Long delivererID);

    void completeDelivery(Long deliveryID);

    void createDelivery(Long orderID);

    void assignDeliverer(Long deliveryID, Long deliverer);

    void unassignDeliverer(Long deliveryID);

    List<Delivery> findAll();

}
