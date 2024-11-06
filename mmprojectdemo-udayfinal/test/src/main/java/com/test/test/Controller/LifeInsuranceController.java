package com.test.test.Controller;


import com.test.test.Entity.LifeInsurance;
import com.test.test.Service.EmailService;
import com.test.test.Service.LifeInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/lifeinsurance")
public class LifeInsuranceController {

    @Autowired
    private LifeInsuranceService service;

    @Autowired
    private EmailService emailService;

    // Get all policies (accessible to ADMIN only)
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<LifeInsurance> getAllPolicies() {
        return service.getAllPolicies();
    }

    // Get policies by User ID
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public List<LifeInsurance> getPoliciesByUserId(@PathVariable Long userId) {
        return service.getPoliciesByUserId(userId);
    }

    // Get policies by Agent ID
    @GetMapping("/agent/{agentId}")
    @PreAuthorize("hasRole('AGENT')")
    public List<LifeInsurance> getPoliciesByAgentId(@PathVariable Long agentId) {
        return service.getPoliciesByAgentId(agentId);
    }

    // Get policy by Policy Number
    @GetMapping("/{policyNumber}")
    @PreAuthorize("hasAnyRole('USER', 'AGENT', 'ADMIN')")
    public LifeInsurance getPolicyByPolicyNumber(@PathVariable String policyNumber) {
        return service.getPolicyByPolicyNumber(policyNumber);
    }

    // Create or Update Policy (accessible to USER and AGENT)
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('USER', 'AGENT')")
    public LifeInsurance createOrUpdatePolicy(@RequestBody LifeInsurance policy) {
        return service.saveOrUpdatePolicy(policy);
    }

    // Update policy by policy number
    @PutMapping("/update/{policyNumber}")
    @PreAuthorize("hasAnyRole('USER', 'AGENT')")
    public ResponseEntity<LifeInsurance> updatePolicy(
            @PathVariable String policyNumber,
            @RequestBody LifeInsurance updatedPolicy) {
        try {
            LifeInsurance updatedLifeInsurance = service.updatePolicy(policyNumber, updatedPolicy);
            return new ResponseEntity<>(updatedLifeInsurance, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Policy not found
        }
    }


    // Delete policy by Policy Number (accessible to ADMIN only)
    @DeleteMapping("/delete/{policyNumber}")
    @PreAuthorize("hasRole('AGENT')")
    public String deletePolicy(@PathVariable String policyNumber) {
        service.deletePolicy(policyNumber);
        return "Policy deleted successfully: " + policyNumber;
    }

    @PostMapping("/send_email")
    @PreAuthorize("hasAnyRole('USER', 'AGENT')")
    public ResponseEntity<String> sendPolicyEmail(@RequestBody Map<String, Object> data) {
        String receiverEmail = (String) data.get("userEmail");
        String policyNumber = (String) data.get("policyNumber");
        String coverageAmount = (String) data.get("coverageAmount");
        String startDate = (String) data.get("startDate");
        String endDate = (String) data.get("endDate");

        String subject = "Policy Creation Confirmation";
        String body = String.format("Dear User,\n\nYour policy %s has been successfully created.\n\n" +
                        "Details:\nCoverage Amount: %s\nStart Date: %s\nEnd Date: %s\n\nThank you!",
                policyNumber, coverageAmount, startDate, endDate);

        emailService.sendEmail(receiverEmail, subject, body);
        return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully");
    }


}
