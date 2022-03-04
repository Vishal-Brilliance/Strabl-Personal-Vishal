package com.strabl.sdk.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Where(clause = "is_deleted is null or is_deleted = false")
@SuperBuilder
@NoArgsConstructor
public class Cart extends BaseEntity {

    @PrePersist
    private void onCreate() {
        setUuid(UUID.randomUUID());
    }
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToMany(mappedBy = "cartList")
    @JsonIgnore
    private List<Orders> ordersList;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "cart_product", joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList = new ArrayList<>();

    public void addProductToCart(Product product) {
        productList.add(product);
        product.getCartList().add(this);
    }

    public void deleteProductFromCart(Product product) {
        for (Product p : productList) {
            if (p.getId().equals(product.getId())) {
                productList.remove(p);
                product.getCartList().remove(this);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Cart{}";
    }
}
