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
    public Order createOrderFromJwtToken(String jwtToken, int discount) throws RuntimeException {
        Account account = userService.findAccountByToken(jwtToken).orElse(null);
        if (account == null) {
            throw new InvalidJwtTokenException();
        }
        Customer customer = account.getCustomer();

        // Find all CartItems by userId
        List<CartItem> cartItems = cartItemRepository.findAllByCustomerId(customer.getId());

        // Check if any cart item is out of stock
        for (CartItem cartItem : cartItems) {
            if (cartItem.getQuantity() > cartItem.getJewelry().getDiamond().getQuantity()) {
                throw new RuntimeException(cartItem.getJewelry().getName() + " is out of stock.");
            }
        }

        Order order = orderItemService.createOrderItemsByCartItems(cartItems, customer, discount);

        if (order == null) {
            throw new RuntimeException("Cart is empty.");
        }

        deliveryService.createDelivery(order.getId());

        return order;
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
