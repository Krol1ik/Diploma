package com.vlad.tms.diploma.controller;

import com.vlad.tms.diploma.model.entity.User;
import com.vlad.tms.diploma.model.order.OrderItem;
import com.vlad.tms.diploma.model.product.Product;
import com.vlad.tms.diploma.service.OrderItemService;
import com.vlad.tms.diploma.service.ProductService;
import com.vlad.tms.diploma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping ("/admin")
public class AdminController {

    @Value("${upload.path}")  //данная аннатация ищет в properties "upload.path" и вставляет в переменную ниже
    private String uploadPath;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderItemService orderItemService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping ("/userList")
    public String adminPage(Model model){

        List<User> userList = userService.findAll();

        List<Double> priceOrderList = new ArrayList<>();
        Double priceOrders = 0.0;
        List<Integer> countProductList = new ArrayList<>();
        int countProduct = 0;
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            List<OrderItem> orderItems = orderItemService.historyOrderForUser(user);
            for (int j = 0; j < orderItems.size(); j++) {
                priceOrders = priceOrders + orderItems.get(j).getPriceOrder();
                countProduct = countProduct + orderItems.get(j).getCount();
            }
            priceOrderList.add(priceOrders);
            countProductList.add(countProduct);
            priceOrders = 0.0;
            countProduct = 0;
       }

        model.addAttribute("priceOrder", priceOrderList);
        model.addAttribute("countOrder", countProductList);
        model.addAttribute("user", userService.findAll());
        return "admin/userList";
    }

    @GetMapping ("/addProduct")
    public String getProduct (Model model){
        model.addAttribute("product", new Product());

        return "admin/addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct (@RequestParam ("file") MultipartFile file,
                              @RequestParam ("brandName") String brand,
                              @RequestParam ("modelName") String modelProduct,
                              @RequestParam ("categoryName") String category,
                              @RequestParam ("typeName") String type,
                              @RequestParam ("descriptionProduct") String description,
                              @RequestParam ("price") Double price,
                              @RequestParam ("discount") int discount,
                              Product product, Model model) throws IOException {

            if (file != null && !file.getOriginalFilename().isEmpty()) { // если файл не равен 0, то мы его добавим
            String resultFilename = file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));

            productService.addFromAdmin(brand, modelProduct, type, category, description, price, discount, resultFilename);

            model.addAttribute("messages", "Товар был добавлен");
            return "admin/addProduct";
        } else {
            model.addAttribute("messages", "Товар не был добавлен, неверно заполнена форма.");
            return "admin/addProduct";
        }
    }
}
