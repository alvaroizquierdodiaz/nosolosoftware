package com.nosolosoftware.bookings.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bookings")
public class Bookings {

  @Id
  @GeneratedValue
  @Column(nullable = false)
  private int id;

  @JoinColumn(name = "hotelsId")
  @ManyToOne(cascade = CascadeType.ALL, optional = false)
  private Hotels hotels;

  @Column(nullable = false)
  @Temporal(TemporalType.DATE)
  private Date fromDate;

  @Column(nullable = false)
  @Temporal(TemporalType.DATE)
  private Date toDate;

  @Column(nullable = false)
  @Email
  private String email;

}
