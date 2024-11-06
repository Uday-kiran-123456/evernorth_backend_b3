package com.test.test.Repository;


import com.test.test.Entity.MotorInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotorInsuranceRepository extends JpaRepository<MotorInsurance, Long> {
    List<MotorInsurance> findByUserId(Long userId);
    List<MotorInsurance> findByAgentId(Long agentId);
    MotorInsurance findByPolicyNumber(String policyNumber);
}
