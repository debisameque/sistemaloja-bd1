package com.smd.umake.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="Sale")
@Table(name="sale")
public class Sale{

  @Id
  @Column(name="id_sale")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @JsonBackReference
  @ManyToOne(fetch= FetchType.LAZY)
  @JoinColumn(name="id_seller")
  private Seller seller;

  @JsonBackReference
  @ManyToOne(fetch= FetchType.LAZY)
  @JoinColumn(name="id_client")
  private Client client;

  @JsonBackReference
  @ManyToOne(fetch= FetchType.LAZY)
  @JoinColumn(name="id_branch")
  private Branch branch;

  @Column(name="total")
  private BigDecimal total;

  @JsonManagedReference
  @OneToMany(mappedBy = "sale",cascade = CascadeType.ALL)
  private List<SaleProduct> sale_products;

}
