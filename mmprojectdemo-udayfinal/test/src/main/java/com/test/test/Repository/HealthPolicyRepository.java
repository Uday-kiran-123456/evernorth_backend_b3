package com.test.test.Repository;


import com.test.test.Entity.HealthPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthPolicyRepository extends JpaRepository<HealthPolicy, Long> {
    List<HealthPolicy> findByUserId(Long userId);
    List<HealthPolicy> findByAgentId(Long agentId);
    HealthPolicy findByPolicyNumber(String policyNumber);

}
