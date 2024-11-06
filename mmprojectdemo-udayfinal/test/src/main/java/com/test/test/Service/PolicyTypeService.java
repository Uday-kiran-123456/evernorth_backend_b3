package com.test.test.Service;

import com.test.test.Entity.PolicyType;
import com.test.test.Repository.PolicyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyTypeService {

    @Autowired
    private PolicyTypeRepository policyTypeRepository;

    // Create or update policy type
    public PolicyType createOrUpdatePolicyType(PolicyType policyType) {
        return policyTypeRepository.save(policyType);
    }

    // Get all policy types
    public List<PolicyType> getAllPolicyTypes() {
        return policyTypeRepository.findAll();
    }

    // Get policy type by ID
    public Optional<PolicyType> getPolicyTypeById(Long id) {
        return policyTypeRepository.findById(id);
    }

    // Delete policy type by ID
    public void deletePolicyType(Long id) {
        policyTypeRepository.deleteById(id);
    }
}

