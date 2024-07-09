package vn.fpt.diamond_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.constant.EOrderStatus;
import vn.fpt.diamond_shop.model.entity.Delivery;
import vn.fpt.diamond_shop.repository.IDeliveryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService implements IDeliveryService {

    private final IDeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryService(IDeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public List<Delivery> getDeliveries(Long delivererID) {
        return deliveryRepository.findAllByDelivererId(delivererID);
    }

    @Override
    public String checkDeliveryStatus(Long deliveryID) {
        Delivery delivery = deliveryRepository.getById(deliveryID);
        delivery.setStatus(EOrderStatus.DONE);
        return "Order is done!";
    }
}
