package com.test.test.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "claims")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String admissionName;
    private String policyHolderName;
    private String policyType;
    private Double coverageAmount;
    private String policyNumber;
    private String agentName;
    private String userEmail;
    private String agentEmail;
    private String status;
    private Long userId;   // User ID associated with the claim
    private Long agentId;  // Agent ID associated with the claim
}
