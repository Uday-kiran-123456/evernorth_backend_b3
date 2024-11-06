package com.test.test.Repository;

import com.test.test.Entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByUserId(Long userId); // Method to find claims by user ID
    List<Claim> findByAgentId(Long agentId);
}
