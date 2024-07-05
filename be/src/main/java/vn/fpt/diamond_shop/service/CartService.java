package vn.fpt.diamond_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.dto.AddToCartRequest;
import vn.fpt.diamond_shop.model.entity.CartItem;
import vn.fpt.diamond_shop.model.entity.Jewelry;
import vn.fpt.diamond_shop.model.entity.JewelrySize;
import vn.fpt.diamond_shop.model.entity.User;
import vn.fpt.diamond_shop.repository.ICartItemRepository;
import vn.fpt.diamond_shop.repository.IJewelryRepository;
import vn.fpt.diamond_shop.repository.IJewelrySizeRepository;
import vn.fpt.diamond_shop.repository.IUserRepository;
import vn.fpt.diamond_shop.security.JwtTokenProvider;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CartService implements ICartService {
    private final ICartItemRepository cartItemRepository;
    private final IJewelryRepository jewelryRepository;
    private final IUserRepository userRepository;
    private final IJewelrySizeRepository jewelrySizeRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public CartService(ICartItemRepository cartItemRepository,
                       IJewelryRepository jewelryRepository,
                       IUserRepository userRepository,
                       IJewelrySizeRepository jewelrySizeRepository,
                       JwtTokenProvider jwtTokenProvider) {
        this.cartItemRepository = cartItemRepository;
        this.jewelryRepository = jewelryRepository;
        this.userRepository = userRepository;
        this.jewelrySizeRepository = jewelrySizeRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void addToCart(String token, Long jewelryId, Integer quantity, Long sizeId) throws NoSuchElementException {
        // Extract username from the token
        String username = jwtTokenProvider.getUsernameFromJWT(token);

        // Find the user by username
        User user = userRepository.findByUsername(username)
                .orElseThrow();
        // Find the Jewelry by jewelryId
        Jewelry jewelry = jewelryRepository.findById(jewelryId)
                .orElseThrow();
        // Find the JewelrySize by sizeId
        JewelrySize size = jewelrySizeRepository.findById(sizeId)
                .orElseThrow();

        // Check if CartItem exists for the given User and Jewelry
        List<CartItem> existingCartItems = cartItemRepository.findByUserIdAndJewelryIdAndSizeId(user.getId(), jewelry.getId(), sizeId);

        CartItem cartItem;
        if (!existingCartItems.isEmpty()) {
            // If exists, update the quantity of the existing CartItem
            cartItem = existingCartItems.get(0);
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            // If does not exist, create a new CartItem and set its properties
            cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setJewelry(jewelry);
            cartItem.setQuantity(quantity);
            cartItem.setSize(size);
            cartItem.setCreateAt(LocalDateTime.now());
        }

        // Save the CartItem to the database
        cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> getCartByUserId(String token) throws RuntimeException {
        // Extract username from the token
        boolean tokenValid = jwtTokenProvider.validateToken(token);

        if (!tokenValid) {
            throw new RuntimeException("Invalid token");
        }

        String username = jwtTokenProvider.getUsernameFromJWT(token);

        // Find the user by username
        User user = userRepository.findByUsername(username)
                .orElseThrow();

        // Return the cart items for the found user
        return cartItemRepository.findAllByUserId(user.getId());
    }
}
