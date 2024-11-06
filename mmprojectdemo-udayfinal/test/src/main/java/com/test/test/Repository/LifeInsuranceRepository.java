package com.test.test.Repository;


import com.test.test.Entity.LifeInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LifeInsuranceRepository extends JpaRepository<LifeInsurance, Long> {
    List<LifeInsurance> findByUserId(Long userId);
    List<LifeInsurance> findByAgentId(Long agentId);
    LifeInsurance findByPolicyNumber(String policyNumber);
    void deleteByPolicyNumber(String policyNumber);
}

