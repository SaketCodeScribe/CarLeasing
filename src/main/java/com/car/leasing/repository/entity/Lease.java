package com.car.leasing.repository.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lease")
public class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long leaseId;
    private long userName;
    private long userEmail;
    private long userPhoneNo;
    private long carId;
}
