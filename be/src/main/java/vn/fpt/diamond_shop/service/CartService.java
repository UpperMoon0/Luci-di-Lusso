package vn.fpt.diamond_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.dto.AddToCartRequest;
import vn.fpt.diamond_shop.model.entity.CartItem;
import vn.fpt.diamond_shop.model.entity.Jewelry;
import vn.fpt.diamond_shop.model.entity.User;
import vn.fpt.diamond_shop.repository.ICartItemRepository;
import vn.fpt.diamond_shop.repository.IJewelryRepository;
import vn.fpt.diamond_shop.repository.IUserRepository;
import vn.fpt.diamond_shop.security.JwtTokenProvider;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartService implements ICartService {
    private final ICartItemRepository cartItemRepository;
    private final IJewelryRepository jewelryRepository;
    private final  IUserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public CartService(ICartItemRepository cartItemRepository,
                       IJewelryRepository jewelryRepository,
                       IUserRepository userRepository,
                       JwtTokenProvider jwtTokenProvider) {
        this.cartItemRepository = cartItemRepository;
        this.jewelryRepository = jewelryRepository;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void addToCart(String token, AddToCartRequest request) throws RuntimeException {
        // Extract username from the token
        String username = jwtTokenProvider.getUsernameFromJWT(token);

        // Find the user by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Jewelry jewelry = jewelryRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Jewelry not found"));

        // Check if CartItem exists for the given User and Jewelry
        List<CartItem> existingCartItems = cartItemRepository.findByUserIdAndJewelryId(user.getId(), jewelry.getId());

        CartItem cartItem;
        if (!existingCartItems.isEmpty()) {
            // If exists, update the quantity of the existing CartItem
            cartItem = existingCartItems.get(0); // Assuming there's only one CartItem for a given User and Jewelry
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
        } else {
            // If does not exist, create a new CartItem and set its properties
            cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setJewelry(jewelry);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setCreateAt(LocalDateTime.now());
        }

        // Save the CartItem to the database
        cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> getCartByUserId(String token) {
        // Extract username from the token
        String username = jwtTokenProvider.getUsernameFromJWT(token);

        // Find the user by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Return the cart items for the found user
        return cartItemRepository.findAllByUserId(user.getId());
    }
}
