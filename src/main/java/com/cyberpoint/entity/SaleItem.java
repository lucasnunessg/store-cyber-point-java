package com.cyberpoint.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sales_item")
public class SaleItem {

  @Id
  @GeneratedValue
  private Long id;

  private Long quantify;

  @ManyToOne
  @JoinColumn(name = "sale_id")
  private Sales sale;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;


  public SaleItem() {
  }


  public SaleItem(Long quantify) {
    this.quantify = quantify;

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getQuantify() {
    return quantify;
  }

  public void setQuantify(Long quantify) {
    this.quantify = quantify;
  }

  public Sales getSale() {
    return sale;
  }

  public void setSale(Sales sale) {
    this.sale = sale;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}
