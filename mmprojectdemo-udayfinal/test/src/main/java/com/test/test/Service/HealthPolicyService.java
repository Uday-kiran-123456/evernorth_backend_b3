package com.test.test.Service;

import com.test.test.Entity.HealthPolicy;
import com.test.test.Repository.HealthPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthPolicyService {

    @Autowired
    private HealthPolicyRepository healthPolicyRepository;

    public List<HealthPolicy> getAllHealthPolicies() {
        return healthPolicyRepository.findAll();
    }

    public List<HealthPolicy> getPoliciesByUserId(Long userId) {
        return healthPolicyRepository.findByUserId(userId);
    }

    public List<HealthPolicy> getPoliciesByAgentId(Long agentId) {
        return healthPolicyRepository.findByAgentId(agentId);
    }
    public HealthPolicy createHealthPolicy(HealthPolicy healthPolicy) {
        return healthPolicyRepository.save(healthPolicy);
    }
    public HealthPolicy getPolicyByPolicyNumber(String policyNumber) {
        return healthPolicyRepository.findByPolicyNumber(policyNumber);
    }

    public HealthPolicy updatePolicy(String policyNumber, HealthPolicy updatedPolicy) {
        HealthPolicy existingPolicy = healthPolicyRepository.findByPolicyNumber(policyNumber);
        if (existingPolicy != null) {
            // Update fields with the new values
            existingPolicy.setAgentId(updatedPolicy.getAgentId());
            existingPolicy.setAgentEmail(updatedPolicy.getAgentEmail());
            existingPolicy.setUserId(updatedPolicy.getUserId());
            existingPolicy.setUserEmail(updatedPolicy.getUserEmail());
            existingPolicy.setMobileNumber(updatedPolicy.getMobileNumber());
            existingPolicy.setStartDate(updatedPolicy.getStartDate());
            existingPolicy.setEndDate(updatedPolicy.getEndDate());
            existingPolicy.setPremiumAmount(updatedPolicy.getPremiumAmount());
            existingPolicy.setCoverageAmount(updatedPolicy.getCoverageAmount());
            existingPolicy.setPaymentFrequency(updatedPolicy.getPaymentFrequency());
            existingPolicy.setPolicyStatus(updatedPolicy.getPolicyStatus());
            existingPolicy.setBeneficiaryDetails(updatedPolicy.getBeneficiaryDetails());
            existingPolicy.setTermsAndConditions(updatedPolicy.getTermsAndConditions());

            // Save the updated policy
            return healthPolicyRepository.save(existingPolicy);
        } else {
            throw new RuntimeException("Policy not found with number: " + policyNumber);
        }
    }

    public boolean deletePolicy(String policyNumber) {
        HealthPolicy existingPolicy = healthPolicyRepository.findByPolicyNumber(policyNumber);
        if (existingPolicy != null) {
            healthPolicyRepository.delete(existingPolicy);
            return true; // Deleted successfully
        }
        return false; // Policy not found
    }

    public HealthPolicy findPolicyByNumber(String policyNumber) {
        return healthPolicyRepository.findByPolicyNumber(policyNumber); // Adjust this line as per your repo method
    }
}

