package com.anhntv.ecom.services.cart;

import com.anhntv.ecom.constants.OrderStatus;
import com.anhntv.ecom.dto.AddProductInCartDTO;
import com.anhntv.ecom.entities.CartItems;
import com.anhntv.ecom.entities.Order;
import com.anhntv.ecom.entities.Product;
import com.anhntv.ecom.entities.User;
import com.anhntv.ecom.repository.CartItemsRepository;
import com.anhntv.ecom.repository.OrderRepository;
import com.anhntv.ecom.repository.ProductRepository;
import com.anhntv.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<?> addProductToCart(AddProductInCartDTO dto) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(dto.getUserId(), OrderStatus.PENDING);
        Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId
                (dto.getProductId(), activeOrder.getId(), dto.getUserId());

        if(optionalCartItems.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } else {
            Optional<Product> optionalProduct = productRepository.findById(dto.getProductId());
            Optional<User> optionalUser = userRepository.findById(dto.getUserId());

            if(optionalProduct.isPresent() && optionalUser.isPresent()) {
                CartItems cart = new CartItems();
                cart.setProduct(optionalProduct.get());
                cart.setPrice(optionalProduct.get().getPrice());
                cart.setQuantity(1L);
                cart.setUser(optionalUser.get());
                cart.setOrder(activeOrder);

                CartItems updateCart = cartItemsRepository.save(cart);

                activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cart.getPrice());
                activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
                activeOrder.getCartItems().add(cart);

                orderRepository.save(activeOrder);

                return ResponseEntity.status(HttpStatus.CREATED).body(cart);

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user or product not found.");
            }
        }
    }
}
