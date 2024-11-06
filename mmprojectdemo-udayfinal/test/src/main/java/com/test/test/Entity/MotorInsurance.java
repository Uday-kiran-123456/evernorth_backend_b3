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
@Table(name = "motorinsurances")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MotorInsurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policyNumber;
    private Long agentId;
    private String agentEmail;
    private Long userId;
    private String userEmail;
    private String mobileNumber;

    private Date startDate;
    private Date endDate;
    private Double premiumAmount;
    private Double coverageAmount;
    private String paymentFrequency;
    private String policyStatus;

    private String vehicleMake;             // Vehicle Make (e.g., Toyota, Honda)
    private String vehicleModel;            // Vehicle Model (e.g., Corolla, Civic)
    private String vehicleRegistrationNumber; // Registration Number
    private String beneficiaryDetails;      // Name, Relation
    private String termsAndConditions;
    private String coverageDetails;         // Details about coverage (e.g., third-party, comprehensive)
}
