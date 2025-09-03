package com.boutique.backend.controller;

import com.boutique.backend.model.User;
import com.boutique.backend.model.Product;
import com.boutique.backend.model.Order;
import com.boutique.backend.repository.UserRepository;
import com.boutique.backend.repository.ProductRepository;
import com.boutique.backend.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ STEP 1: Create admin user at startup
    @PostConstruct
    public void createAdminIfNotExist() {
        if (userRepository.findByEmail("admin@gmail.com") == null) {
            User admin = new User();
            admin.setUsername("admin123");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");
            userRepository.save(admin);
            System.out.println("✅ Admin user created.");
        } else {
            System.out.println("ℹ️ Admin already exists.");
        }
    }

    // ✅ STEP 2: Admin dashboard
    @GetMapping("/dashboard")
    public String getAdminDashboard(@RequestParam Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent() && "ADMIN".equalsIgnoreCase(userOptional.get().getRole())) {
            return "Welcome to the Admin Dashboard!";
        } else {
            return "Access Denied or User Not Found.";
        }
    }

    // ✅ STEP 3: Admin login
    @PostMapping("/login")
    public String adminLogin(@RequestBody User loginData) {
        User user = userRepository.findByEmail(loginData.getEmail());
        if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
            return "Access Denied: Not an Admin.";
        }
        if (passwordEncoder.matches(loginData.getPassword(), user.getPassword())) {
            return "Admin login successful!";
        } else {
            return "Invalid credentials!";
        }
    }

    // ✅ STEP 4: View all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ STEP 5: View all orders
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // ✅ STEP 6: Add new product
    @PostMapping("/add-product")
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // ✅ Optional: View all products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
