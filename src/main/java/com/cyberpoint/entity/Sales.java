package com.cyberpoint.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

  public Sales() {}

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
}
