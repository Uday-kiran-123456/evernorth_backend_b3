package com.test.test.Service;


import com.test.test.Entity.Claim;
import com.test.test.Repository.ClaimRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimService {
    private final ClaimRepository claimRepository;

    public ClaimService(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    public Claim saveClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    public Claim getClaimById(Long id) {
        return claimRepository.findById(id).orElse(null);
    }

    public Claim updateClaim(Long id, Claim claim) {
        if (claimRepository.existsById(id)) {
            claim.setId(id);
            return claimRepository.save(claim);
        }
        return null;
    }

    public boolean deleteClaim(Long id) {
        if (claimRepository.existsById(id)) {
            claimRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Claim> getClaimsByUserId(Long userId) {
        return claimRepository.findByUserId(userId); // Assume this method is implemented in the repository
    }

    // Method to get claims by agent ID
    public List<Claim> getClaimsByAgentId(Long agentId) {
        return claimRepository.findByAgentId(agentId); // Assume this method is implemented in the repository
    }
}
