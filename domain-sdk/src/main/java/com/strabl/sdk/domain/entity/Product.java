package com.strabl.sdk.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Where(clause = "is_deleted is null or is_deleted = false")
@SuperBuilder
@NoArgsConstructor
public class Product extends BaseEntity {

  @PrePersist
  private void onCreate() {
    setUuid(java.util.UUID.randomUUID());
  }

  private UUID uuid;

  @ManyToOne
  @JoinColumn(name = "customer_id", nullable=false)
  private User customer;

  @ManyToOne
  @JoinColumn(name = "address_id", nullable = false)
  private Address address;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @ManyToOne
  @JoinColumn(name = "subcategory_id", nullable = false)
  private Category subCategory;

  @ManyToOne
  @JoinColumn(name = "classification_id")
  private Classification classification;

  @OneToOne
  @JoinColumn(name = "tbyb_id")
  private TBYB tbyb;

  @ManyToMany(mappedBy = "productList")
  @JsonIgnore
  private List<Cart> cartList = new ArrayList<>();

  @ManyToMany(mappedBy = "productList", fetch = FetchType.EAGER)
  @JsonIgnore
  private List<Tags> tagsList;

  private String slug;

  @Column(length = 200)
  @Size(min = 1, max = 200)
  private String title;

  @Column(length = 512)
  @Size(min = 1, max = 512)
  private String description;

  @Size(min = 1, max = 255)
  private String features;

  @Size(min = 1, max = 255)
  private String tags;

  @ManyToOne
  @JoinColumn(name = "currency_id", nullable = false)
  private Currency currency;

  @ManyToOne
  @JoinColumn(name = "seller_id" , nullable = false)
  private User seller;

  @Transient
  private Integer quantity;

  private Long finalPrice;
  private Boolean isActive;
  private Boolean isApproved;
  private Boolean isFeatured;
  private String brand;

  @Override
  public String toString() {
    return "Product{}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    Product product = (Product) o;

    if (uuid != null ? !uuid.equals(product.uuid) : product.uuid != null) return false;
    if (customer != null ? !customer.equals(product.customer) : product.customer != null) return false;
    if (address != null ? !address.equals(product.address) : product.address != null) return false;
    if (category != null ? !category.equals(product.category) : product.category != null) return false;
    if (subCategory != null ? !subCategory.equals(product.subCategory) : product.subCategory != null) return false;
    if (classification != null ? !classification.equals(product.classification) : product.classification != null)
      return false;
    if (cartList != null ? !cartList.equals(product.cartList) : product.cartList != null) return false;
    if (tagsList != null ? !tagsList.equals(product.tagsList) : product.tagsList != null) return false;
    if (tbyb != null ? !tbyb.equals(product.tbyb) : product.tbyb != null) return false;
    if (slug != null ? !slug.equals(product.slug) : product.slug != null) return false;
    if (title != null ? !title.equals(product.title) : product.title != null) return false;
    if (brand != null ? !brand.equals(product.brand) : product.brand != null) return false;
    if (description != null ? !description.equals(product.description) : product.description != null) return false;
    if (features != null ? !features.equals(product.features) : product.features != null) return false;
    if (currency != null ? !currency.equals(product.currency) : product.currency != null) return false;
    if (seller != null ? !seller.equals(product.seller) : product.seller != null) return false;
    if (quantity != null ? !quantity.equals(product.quantity) : product.quantity != null) return false;
    if (finalPrice != null ? !finalPrice.equals(product.finalPrice) : product.finalPrice != null) return false;
    if (isActive != null ? !isActive.equals(product.isActive) : product.isActive != null) return false;
    if (isApproved != null ? !isApproved.equals(product.isApproved) : product.isApproved != null) return false;
    return isFeatured != null ? isFeatured.equals(product.isFeatured) : product.isFeatured == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
    result = 31 * result + (category != null ? category.hashCode() : 0);
    result = 31 * result + (subCategory != null ? subCategory.hashCode() : 0);
    result = 31 * result + (cartList != null ? cartList.hashCode() : 0);
    result = 31 * result + (tagsList != null ? tagsList.hashCode() : 0);
    result = 31 * result + (slug != null ? slug.hashCode() : 0);
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + (brand != null ? brand.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (features != null ? features.hashCode() : 0);
    result = 31 * result + (seller != null ? seller.hashCode() : 0);
    result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
    result = 31 * result + (finalPrice != null ? finalPrice.hashCode() : 0);
    result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
    result = 31 * result + (isApproved != null ? isApproved.hashCode() : 0);
    result = 31 * result + (isFeatured != null ? isFeatured.hashCode() : 0);
    return result;
  }
}
