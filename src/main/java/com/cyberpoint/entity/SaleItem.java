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

  private int quantify;

  @ManyToOne
  @JoinColumn(name = "sale_id")
  private Sales sale;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;


  public SaleItem() {
  }


  public SaleItem(int quantify, Sales sale, Product product) {
    this.quantify = quantify;
    this.sale = sale;
    this.product = product;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getQuantify() {
    return quantify;
  }

  public void setQuantify(int quantify) {
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
