package com.smd.umake.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name="Client")
@Table(name="client")
class Client{

  @Id
  @Column(name="id_client")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name="name", length=60, nullable=false)
  private String name;

  // WARN: PAREI AQUI
  @OneToMany(mappedBy="client",cascade=CascadeType.ALL, orphanRemoval = true)
  private List<ClientContact> contacts;

  @OneToMany(mappedBy="client",cascade=CascadeType.ALL)
  private Set<Sale> shopping = new HashSet<>();

}

@Entity(name="ClientContact")
class ClientContact{

  @Id
  @ManyToOne(fetch= FetchType.LAZY)
  @JoinColumn(name="id_client")
  private Client client;
  @Id
  private String ddd;
  @Id
  private String phoneNumber;


}
