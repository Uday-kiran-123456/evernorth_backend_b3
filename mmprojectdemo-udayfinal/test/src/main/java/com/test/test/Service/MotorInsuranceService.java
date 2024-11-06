package com.test.test.Service;

import com.test.test.Entity.MotorInsurance;
import com.test.test.Repository.MotorInsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorInsuranceService {

    @Autowired
    private MotorInsuranceRepository motorInsuranceRepository;

    public List<MotorInsurance> getAllPolicies() {
        return motorInsuranceRepository.findAll();
    }

    public List<MotorInsurance> getPoliciesByUserId(Long userId) {
        return motorInsuranceRepository.findByUserId(userId);
    }

    public List<MotorInsurance> getPoliciesByAgentId(Long agentId) {
        return motorInsuranceRepository.findByAgentId(agentId);
    }

    public MotorInsurance getPolicyByPolicyNumber(String policyNumber) {
        return motorInsuranceRepository.findByPolicyNumber(policyNumber);
    }

    public MotorInsurance createPolicy(MotorInsurance motorInsurance) {
        return motorInsuranceRepository.save(motorInsurance);
    }

    public MotorInsurance updatePolicy(String policyNumber, MotorInsurance updatedPolicy) {
        MotorInsurance existingPolicy = getPolicyByPolicyNumber(policyNumber);
        if (existingPolicy != null) {
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
            existingPolicy.setVehicleMake(updatedPolicy.getVehicleMake());
            existingPolicy.setVehicleModel(updatedPolicy.getVehicleModel());
            existingPolicy.setVehicleRegistrationNumber(updatedPolicy.getVehicleRegistrationNumber());
            existingPolicy.setBeneficiaryDetails(updatedPolicy.getBeneficiaryDetails());
            existingPolicy.setTermsAndConditions(updatedPolicy.getTermsAndConditions());
            existingPolicy.setCoverageDetails(updatedPolicy.getCoverageDetails());

            return motorInsuranceRepository.save(existingPolicy);
        }
        return null; // or throw an exception
    }

    public boolean deletePolicy(String policyNumber) {
        MotorInsurance policy = getPolicyByPolicyNumber(policyNumber);
        if (policy != null) {
            motorInsuranceRepository.delete(policy);
            return true;
        }
        return false; // or throw an exception
    }
}

