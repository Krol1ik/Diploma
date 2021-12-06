package com.vlad.tms.diploma.controller;

import com.vlad.tms.diploma.model.entity.User;
import com.vlad.tms.diploma.model.order.OrderItem;
import com.vlad.tms.diploma.model.product.Product;
import com.vlad.tms.diploma.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping ("/admin")
public class AdminController {

    @Value("${upload.path}")  //данная аннатация ищет в properties "upload.path" и вставляет в переменную ниже
    private String uploadPath;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TypeService typeService;
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
        model.addAttribute("allCategory", categoryService.categoryAll());
        model.addAttribute("allType", typeService.typeAll());
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
                              @ModelAttribute Product product, Model model) throws IOException {

        if (file != null && !file.getOriginalFilename().isEmpty()) { // если файл не равен 0, то мы его добавим в класс
            File uploadDir = new File(uploadPath);

            if (uploadDir.exists()) {   //Если uploadDir не существует, то мы ее создаем
                uploadDir.mkdir();
            }
            //обезопасили себя от коолизии и создаем уникальное имя файла
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));   //загружаем наш файл
            product.setFilename("/img/" + resultFilename);
            productService.addFromAdmin(brand, modelProduct, type, category, description, price, discount, product);

            return "admin/addProduct";
        } else {
            model.addAttribute("messages", "Товар не был добавлен, неверно заполнена форма.");
            return "admin/addProduct";
        }
    }
}
