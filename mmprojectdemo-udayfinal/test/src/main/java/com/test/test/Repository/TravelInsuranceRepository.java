package com.test.test.Repository;


import com.test.test.Entity.TravelInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TravelInsuranceRepository extends JpaRepository<TravelInsurance, Long> {
    List<TravelInsurance> findByUserId(Long userId);
    List<TravelInsurance> findByAgentId(Long agentId);
    TravelInsurance findByPolicyNumber(String policyNumber);
}
