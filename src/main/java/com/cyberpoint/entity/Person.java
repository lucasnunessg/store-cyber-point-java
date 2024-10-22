package com.cyberpoint.entity;

import com.cyberpoint.security.Role;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import java.util.Collection;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "person")
public class Person implements UserDetails {

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
  private Role role;

  @OneToMany(mappedBy = "person")
  private List<Product> products;

  public Person(String fullname, String username, String email, String password,
      String address, Role role) {
    this.fullname = fullname;
    this.username = username;
    this.email = email;
    this.password = password;
    this.address = address;
    this.role = role;

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

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
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

  // @Override
  // public Collection<? extends GrantedAuthority> getAuthorities() {
  //   return List.of();
  // }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
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

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
