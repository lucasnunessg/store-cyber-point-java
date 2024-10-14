package com.cyberpoint.entity;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
public class Person {

  @Id
  @GeneratedValue
  private Long id;
  private String fullname;
  @Column(unique = true)
  private String username;
  @Column(unique = true)
  private String email;
  private String password;
  private String address;

  @OneToMany(mappedBy = "person")
  private List<Product> products;

  public Person(String fullname, String username, String email, String password,
      String address) {
    this.fullname = fullname;
    this.username = username;
    this.email = email;
    this.password = password;
    this.address = address;
  }

  public Person() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }
}
