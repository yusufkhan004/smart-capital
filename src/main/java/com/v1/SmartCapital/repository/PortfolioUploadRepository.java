package com.v1.SmartCapital.repository;

import com.v1.SmartCapital.entity.PortfolioDetails;
import org.springframework.data.jpa.repository.JpaRepository;


//TODO This is wrong, repo name should PortfolioDetailsRepository just for easy understanding and searching quickly / same goes for all entity others as well
//TODO and also mention the annotation
public interface PortfolioUploadRepository extends JpaRepository<PortfolioDetails,Long> {

}
