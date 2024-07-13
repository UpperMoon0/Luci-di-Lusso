package vn.fpt.diamond_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.constant.EOrderStatus;
import vn.fpt.diamond_shop.model.entity.Delivery;
import vn.fpt.diamond_shop.model.entity.Order;
import vn.fpt.diamond_shop.model.entity.User;
import vn.fpt.diamond_shop.repository.IDeliveryRepository;
import vn.fpt.diamond_shop.repository.IOrderRepository;
import vn.fpt.diamond_shop.repository.IUserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryService implements IDeliveryService {

    private final IDeliveryRepository deliveryRepository;

    private final IOrderRepository orderRepository;

    private final IUserRepository userRepository;

    @Autowired
    public DeliveryService(IDeliveryRepository deliveryRepository, IOrderRepository orderRepository, IUserRepository userRepository) {
        this.deliveryRepository = deliveryRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Delivery> getDeliveriesByUser(Long delivererID) {
        return deliveryRepository.findAllByDelivererId(delivererID);
    }

    @Override
    public void completeDelivery(Long deliveryID) {
        Delivery delivery = deliveryRepository.getById(deliveryID);
        delivery.setStatus(EOrderStatus.DONE);
        deliveryRepository.save(delivery);
    }

    @Override
    public void createDelivery(Long orderID) {
        Order order = orderRepository.getById(orderID);
        Delivery delivery = new Delivery();
        LocalDateTime dateTime = LocalDate.now().atStartOfDay();

        delivery.setOrder(order);
        delivery.setCreateAt(dateTime);
        delivery.setStatus(EOrderStatus.PENDING);

        deliveryRepository.save(delivery);
    }

    @Override
    public void assignDeliverer(Long deliveryID, Long delivererID) {
        Delivery delivery = deliveryRepository.getById(deliveryID);
        User user = userRepository.getById(delivererID);
        delivery.setDeliverer(user);
        deliveryRepository.save(delivery);
    }

    @Override
    public List<Delivery> getUnassignedDeliveries() {
        return deliveryRepository.findAllByDelivererIsNull();
    }
}
