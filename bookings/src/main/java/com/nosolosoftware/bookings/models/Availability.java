package com.nosolosoftware.bookings.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "availability")
public class Availability {

  @Id
  @Temporal(TemporalType.DATE)
  @Column(nullable = false)
  private Date date;

  @Column(nullable = false)
  private int rooms;

  @JoinColumn(name = "hotelsId")
  @ManyToOne(cascade = CascadeType.ALL, optional = false)
  private Hotels hotels;
}
