package vn.fpt.diamond_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.entity.Delivery;
import vn.fpt.diamond_shop.model.entity.Order;
import vn.fpt.diamond_shop.model.entity.Account;
import vn.fpt.diamond_shop.repository.IDeliveryRepository;
import vn.fpt.diamond_shop.repository.IOrderRepository;
import vn.fpt.diamond_shop.repository.IAccountRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryService implements IDeliveryService {

    private final IDeliveryRepository deliveryRepository;

    private final IOrderRepository orderRepository;

    private final IAccountRepository userRepository;

    @Autowired
    public DeliveryService(IDeliveryRepository deliveryRepository, IOrderRepository orderRepository, IAccountRepository userRepository) {
        this.deliveryRepository = deliveryRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Delivery> findByAccount(Long delivererID) {
        return deliveryRepository.findAllByDelivererId(delivererID);
    }

    @Override
    public void completeDelivery(Long deliveryID) {
        Delivery delivery = deliveryRepository.getById(deliveryID);
        delivery.setStatus("COMPLETED");
        deliveryRepository.save(delivery);
    }

    @Override
    public void createDelivery(Long orderID) {
        Order order = orderRepository.getById(orderID);
        Delivery delivery = new Delivery();
        LocalDateTime dateTime = LocalDate.now().atStartOfDay();

        delivery.setOrder(order);
        delivery.setCreateAt(dateTime);
        delivery.setStatus("UNASSIGNED");

        deliveryRepository.save(delivery);
    }

    @Override
    public void assignDeliverer(Long deliveryID, Long delivererID) {
        Delivery delivery = deliveryRepository.getById(deliveryID);
        Account user = userRepository.getById(delivererID);
        delivery.setDeliverer(user);
        delivery.setStatus("ASSIGNED");
        deliveryRepository.save(delivery);
    }

    @Override
    public void unassignDeliverer(Long deliveryID) {
        Delivery delivery = deliveryRepository.getById(deliveryID);
        delivery.setDeliverer(null);
        delivery.setStatus("UNASSIGNED");
        deliveryRepository.save(delivery);
    }

    @Override
    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }
}
