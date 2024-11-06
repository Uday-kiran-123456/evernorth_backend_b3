package com.test.test.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "healthpolicies")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HealthPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policyNumber;
    private Long agentId;
    private String agentEmail;
    private Date startDate;
    private Date endDate;
    private Long userId;
    private String userEmail;
    private String mobileNumber;

    private Double premiumAmount;
    private Double coverageAmount;
    private String paymentFrequency;
    private String policyStatus;

    private Double claimLimit;
    private String beneficiaryDetails;
    private String termsAndConditions;

    private String policyType; // e.g., Individual, Family, Critical Illness
    private String preExistingDiseases; // Pre-existing diseases coverage
}
