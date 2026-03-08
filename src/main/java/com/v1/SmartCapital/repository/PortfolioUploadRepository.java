package com.v1.SmartCapital.repository;

import com.v1.SmartCapital.entity.PortfolioDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioUploadRepository extends JpaRepository<PortfolioDetails,Long> {

}
