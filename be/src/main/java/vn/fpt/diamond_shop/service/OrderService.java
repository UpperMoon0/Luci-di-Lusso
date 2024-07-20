package vn.fpt.diamond_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.exception.InvalidJwtTokenException;
import vn.fpt.diamond_shop.model.entity.*;
import vn.fpt.diamond_shop.repository.ICartItemRepository;
import vn.fpt.diamond_shop.repository.IOrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    private final IOrderRepository orderRepository;
    private final ICartItemRepository cartItemRepository;
    private final IOrderItemService orderItemService;
    private final IAccountService userService;
    private final IDeliveryService deliveryService;
    private final IJewelryService jewelryService;

    @Autowired
    public OrderService(IOrderRepository orderRepository,
                        ICartItemRepository cartItemRepository,
                        IOrderItemService orderItemService,
                        IAccountService userService,
                        IDeliveryService deliveryService,
                        IJewelryService jewelryService) {
        this.orderRepository = orderRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderItemService = orderItemService;
        this.userService = userService;
        this.deliveryService = deliveryService;
        this.jewelryService = jewelryService;
    }

    @Override
    public double createOrderFromJwtToken(String jwtToken) throws InvalidJwtTokenException {
        Account account = userService.findAccountByToken(jwtToken).orElse(null);
        if (account == null) {
            throw new InvalidJwtTokenException();
        }
        Customer customer = account.getCustomer();

        Order order = new Order();
        order.setCustomer(customer);
        order.setCreateAt(LocalDateTime.now());
        orderRepository.save(order);

        // Find all CartItems by userId
        List<CartItem> cartItems = cartItemRepository.findAllByCustomerId(customer.getId());
        orderItemService.createOrderItemsByCartItems(cartItems, order);

        // Calculate total price
        double totalPrice = 0.0;
        for (CartItem cartItem : cartItems) {
            totalPrice += jewelryService.calculateJewelryPriceWithSize(cartItem.getJewelry(), cartItem.getJewelrySize()) * cartItem.getQuantity();
        }

        deliveryService.createDelivery(order.getId());

        return totalPrice;
    }

    @Override
    public List<Order> getOrdersByCustomer(Long customerId) {
        return orderRepository.findAllByCustomerId(customerId);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}
