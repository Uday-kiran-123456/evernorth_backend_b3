package com.test.test.Service;

import com.test.test.Entity.LifeInsurance;
import com.test.test.Repository.LifeInsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LifeInsuranceService {

    @Autowired
    private LifeInsuranceRepository repository;

    // Create or Update Life Insurance Policy
    public LifeInsurance saveOrUpdatePolicy(LifeInsurance policy) {
        return repository.save(policy);
    }

    // Get all Life Insurance Policies
    public List<LifeInsurance> getAllPolicies() {
        return repository.findAll();
    }

    // Get policies by User ID
    public List<LifeInsurance> getPoliciesByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    // Get policies by Agent ID
    public List<LifeInsurance> getPoliciesByAgentId(Long agentId) {
        return repository.findByAgentId(agentId);
    }

    // Get policy by Policy Number
    public LifeInsurance getPolicyByPolicyNumber(String policyNumber) {
        return repository.findByPolicyNumber(policyNumber);
    }

    // Delete policy by Policy Number
    public void deletePolicy(String policyNumber) {
        repository.deleteByPolicyNumber(policyNumber);
    }

    // Update policy
    public LifeInsurance updatePolicy(String policyNumber, LifeInsurance updatedPolicy) {
        LifeInsurance existingPolicy = repository.findByPolicyNumber(policyNumber);
        if (existingPolicy != null) {
            // Update existing policy fields with new values
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

            // Save updated policy
            return repository.save(existingPolicy);
        } else {
            throw new RuntimeException("Policy not found with number: " + policyNumber);
        }
    }
}
