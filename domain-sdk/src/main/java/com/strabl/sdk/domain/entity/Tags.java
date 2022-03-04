package com.strabl.sdk.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tags")
@Where(clause = "is_deleted is null or is_deleted = false")
@SuperBuilder
@NoArgsConstructor
public class Tags extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "tags_product", joinColumns = @JoinColumn(name = "tags_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList;

    public void attachTagsToProduct(Product product) {
        if (!product.getTagsList().contains(this)){
            productList.add(product);
            product.getTagsList().add(this);
        }
    }

    public void removeTagsFromProduct(Product product) {
        for (Product p : productList) {
            if (p.getId().equals(product.getId())) {
                productList.remove(p);
                product.getTagsList().remove(this);
                break;
            }
        }
    }
}
