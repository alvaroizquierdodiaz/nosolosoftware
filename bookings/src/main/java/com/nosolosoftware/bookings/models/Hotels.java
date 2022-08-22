package com.nosolosoftware.bookings.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hotels")
public class Hotels {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private int id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private int category;

  @OneToMany(mappedBy = "hotels", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<Availability> availabilities = new HashSet<>();

}
