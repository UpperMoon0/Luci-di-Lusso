package vn.fpt.diamond_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.fpt.diamond_shop.exception.InvalidJwtTokenException;
import vn.fpt.diamond_shop.model.entity.*;
import vn.fpt.diamond_shop.repository.ICartItemRepository;
import vn.fpt.diamond_shop.repository.IJewelryRepository;
import vn.fpt.diamond_shop.repository.IJewelrySizeRepository;
import vn.fpt.diamond_shop.repository.IAccountRepository;
import vn.fpt.diamond_shop.security.JwtTokenProvider;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class CartService implements ICartService {
    private final ICartItemRepository cartItemRepository;
    private final IJewelryRepository jewelryRepository;
    private final IJewelrySizeRepository jewelrySizeRepository;
    private final AccountService accountService;

    @Override
    public void addToCart(String jwtToken, Long jewelryId, Integer quantity, Long sizeId) throws RuntimeException {
        Account account = accountService.findAccountByToken(jwtToken).orElse(null);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }
        Customer customer = account.getCustomer();

        // Find the Jewelry by jewelryId
        Jewelry jewelry = jewelryRepository.findById(jewelryId)
                .orElseThrow(() -> new RuntimeException("Jewelry not found"));
        // Find the JewelrySize by sizeId
        JewelrySize size = jewelrySizeRepository.findById(sizeId)
                .orElseThrow(() -> new RuntimeException("Size not found"));

        // Check if CartItem exists for the given User and Jewelry
        List<CartItem> existingCartItems = cartItemRepository.findByCustomerIdAndJewelryIdAndJewelrySizeId(customer.getId(), jewelry.getId(), sizeId);

        // Check if jewelries are in stock
        if (jewelry.getDiamond().getQuantity() < quantity) {
            throw new RuntimeException("Not enough jewelry in stock");
        }

        CartItem cartItem;
        if (!existingCartItems.isEmpty()) {
            // If exists, update the quantity of the existing CartItem
            cartItem = existingCartItems.get(0);
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            // If does not exist, create a new CartItem and set its properties
            cartItem = new CartItem();
            cartItem.setCustomer(customer);
            cartItem.setJewelry(jewelry);
            cartItem.setQuantity(quantity);
            cartItem.setJewelrySize(size);
            cartItem.setCreateAt(LocalDateTime.now());
        }

        // Save the CartItem to the database
        cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> getCartByUserId(String jwtToken) throws RuntimeException {
        Account account = accountService.findAccountByToken(jwtToken).orElse(null);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }
        Customer customer = account.getCustomer();

        // Return the cart items for the found user
        return cartItemRepository.findAllByCustomerId(customer.getId());
    }

    @Override
    public void updateCartItem(String jwtToken, Long cartItemId, Integer quantity) throws RuntimeException{
        Account account = accountService.findAccountByToken(jwtToken).orElse(null);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }
        Customer customer = account.getCustomer();

        // Find the CartItem by cartItemId
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        // Check if the CartItem belongs to the user
        if (!cartItem.getCustomer().getId().equals(customer.getId())) {
            throw new RuntimeException("Cart item not found");
        }

        // Update the quantity of the CartItem
        cartItem.setQuantity(quantity);

        // Save the updated CartItem to the database
        cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteCartItem(String jwtToken, Long cartItemId) throws RuntimeException {
        Account account = accountService.findAccountByToken(jwtToken).orElse(null);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }
        Customer customer = account.getCustomer();

        // Find the CartItem by cartItemId
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        // Check if the CartItem belongs to the user
        if (!cartItem.getCustomer().getId().equals(customer.getId())) {
            throw new RuntimeException("Cart item not found");
        }

        // Delete the CartItem from the database
        cartItemRepository.delete(cartItem);
    }

    @Transactional
    @Override
    public void deleteAllCartItems(String jwtToken) throws RuntimeException {
        Account account = accountService.findAccountByToken(jwtToken).orElse(null);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }
        Customer customer = account.getCustomer();

        // Delete all CartItems for the user
        cartItemRepository.deleteAllByCustomerId(customer.getId());
    }
}
