package com.car.leasing.repository;

import com.car.leasing.repository.entity.Lease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaseRepository extends JpaRepository<Lease, Long> {
}
