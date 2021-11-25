package com.vlad.tms.diploma.model.product;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "Article")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Type_product_id")
    private Type type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Brand_product_id")
    private Brand brand;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Model_product_id")
    private Model model;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Category_product_id")
    private Category category;

    @Column(name = "Description_product")
    private String descriptionProduct;
    @Column(name = "Price", nullable = false)
    private Double price;
    @Column(name = "Discount")
    private int discount;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private StockBalance stockBalance;

    private String filename;

    public Product() {
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticle() {
        return article;
    }

    public void setArticle(Long article) {
        this.article = article;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public StockBalance getStockBalance() {
        return stockBalance;
    }

    public void setStockBalance(StockBalance stockBalance) {
        this.stockBalance = stockBalance;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", article=" + article +
                ", type=" + type +
                ", brand=" + brand +
                ", model=" + model +
                ", category=" + category +
                ", descriptionProduct='" + descriptionProduct + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", stockBalance=" + stockBalance +
                ", filename='" + filename + '\'' +
                '}';
    }
}
