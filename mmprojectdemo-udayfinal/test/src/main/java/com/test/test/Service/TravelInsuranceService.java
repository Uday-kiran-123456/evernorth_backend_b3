package com.test.test.Service;


import com.test.test.Entity.TravelInsurance;
import com.test.test.Repository.TravelInsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelInsuranceService {

    @Autowired
    private TravelInsuranceRepository travelInsuranceRepository;

    public List<TravelInsurance> getAllTravelPolicies() {
        return travelInsuranceRepository.findAll();
    }

    public List<TravelInsurance> getTravelPoliciesByUserId(Long userId) {
        return travelInsuranceRepository.findByUserId(userId);
    }

    public List<TravelInsurance> getTravelPoliciesByAgentId(Long agentId) {
        return travelInsuranceRepository.findByAgentId(agentId);
    }

    public TravelInsurance getPolicyByNumber(String policyNumber) {
        return travelInsuranceRepository.findByPolicyNumber(policyNumber);
    }

    public TravelInsurance createTravelPolicy(TravelInsurance travelInsurance) {
        return travelInsuranceRepository.save(travelInsurance);
    }

    public TravelInsurance updateTravelPolicy(String policyNumber, TravelInsurance updatedTravelInsurance) {
        TravelInsurance existingPolicy = travelInsuranceRepository.findByPolicyNumber(policyNumber);
        if (existingPolicy != null) {
            // Update the existing policy fields with the new values
            existingPolicy.setAgentId(updatedTravelInsurance.getAgentId());
            existingPolicy.setAgentEmail(updatedTravelInsurance.getAgentEmail());
            existingPolicy.setUserId(updatedTravelInsurance.getUserId());
            existingPolicy.setUserEmail(updatedTravelInsurance.getUserEmail());
            existingPolicy.setMobileNumber(updatedTravelInsurance.getMobileNumber());
            existingPolicy.setStartDate(updatedTravelInsurance.getStartDate());
            existingPolicy.setEndDate(updatedTravelInsurance.getEndDate());
            existingPolicy.setPremiumAmount(updatedTravelInsurance.getPremiumAmount());
            existingPolicy.setCoverageAmount(updatedTravelInsurance.getCoverageAmount());
            existingPolicy.setPaymentFrequency(updatedTravelInsurance.getPaymentFrequency());
            existingPolicy.setPolicyStatus(updatedTravelInsurance.getPolicyStatus());
            existingPolicy.setBeneficiaryDetails(updatedTravelInsurance.getBeneficiaryDetails());
            existingPolicy.setTravelDestination(updatedTravelInsurance.getTravelDestination());
            existingPolicy.setDepartureDate(updatedTravelInsurance.getDepartureDate());
            existingPolicy.setReturnDate(updatedTravelInsurance.getReturnDate());
            existingPolicy.setTravelPurpose(updatedTravelInsurance.getTravelPurpose());
            existingPolicy.setTermsAndConditions(updatedTravelInsurance.getTermsAndConditions());
            existingPolicy.setMedicalCoverage(updatedTravelInsurance.getMedicalCoverage());
            existingPolicy.setTripCancellationCoverage(updatedTravelInsurance.getTripCancellationCoverage());
            existingPolicy.setRiskCoverDetails(updatedTravelInsurance.getRiskCoverDetails());

            return travelInsuranceRepository.save(existingPolicy); // Save the updated policy
        }
        return null; // Policy not found
    }

    public void deleteTravelPolicy(String policyNumber) {
        TravelInsurance travelInsurance = travelInsuranceRepository.findByPolicyNumber(policyNumber);
        if (travelInsurance != null) {
            travelInsuranceRepository.delete(travelInsurance);
        }
    }
}
