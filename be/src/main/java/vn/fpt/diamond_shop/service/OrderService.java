package vn.fpt.diamond_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.entity.*;
import vn.fpt.diamond_shop.repository.ICartItemRepository;
import vn.fpt.diamond_shop.repository.IOrderRepository;
import vn.fpt.diamond_shop.repository.IUserRepository;
import vn.fpt.diamond_shop.security.JwtTokenProvider;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService implements IOrderService {
    private final IOrderRepository orderRepository;
    private final IUserRepository userRepository;
    private final ICartItemRepository cartItemRepository;
    private final IOrderItemService orderItemService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public OrderService(IOrderRepository orderRepository,
                        IUserRepository userRepository,
                        ICartItemRepository cartItemRepository,
                        IOrderItemService orderItemService,
                        JwtTokenProvider jwtTokenProvider) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderItemService = orderItemService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public double createOrder(String token) {
        // Extract username from the token
        boolean tokenValid = jwtTokenProvider.validateToken(token);

        if (!tokenValid) {
            throw new RuntimeException("Invalid token");
        }

        // Extract username from the token
        String username = jwtTokenProvider.getUsernameFromJWT(token);

        // Find the user by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setCreateAt(LocalDateTime.now());
        orderRepository.save(order);

        // Find all CartItems by userId
        List<CartItem> cartItems = cartItemRepository.findAllByUserId(user.getId());
        orderItemService.createOrderItemsByCartItems(cartItems, order);

        // Calculate total price
        double totalPrice = 0.0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getJewelry().getPrice() * cartItem.getQuantity();
        }

        return totalPrice;
    }
}
