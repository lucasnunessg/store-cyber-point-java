package com.cyberpoint.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.util.List;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class Sales {

  @Id
  @GeneratedValue
  private Long id;

  private LocalDateTime saleDate;

  @ManyToOne
  @JoinColumn(name = "person_id")
  private Person person;

  @OneToMany(mappedBy = "sale")
  private List<SaleItem> saleItems;

  public Sales() {
  }

  public Sales(LocalDateTime saleDate) {
    this.saleDate = LocalDateTime.now();
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public LocalDateTime getSaleDate() {
    return saleDate;
  }

  public void setSaleDate(LocalDateTime saleDate) {
    this.saleDate = saleDate;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public List<SaleItem> getSaleItems() {
    return saleItems;
  }

  public void setSaleItems(List<SaleItem> saleItems) {
    this.saleItems = saleItems;
  }
}
