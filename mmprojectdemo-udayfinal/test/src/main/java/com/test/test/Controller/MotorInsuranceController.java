package com.test.test.Controller;


import com.test.test.Entity.MotorInsurance;
import com.test.test.Service.MotorInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/motorinsurances")
public class MotorInsuranceController {

    @Autowired
    private MotorInsuranceService motorInsuranceService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'AGENT', 'ADMIN')")
    public ResponseEntity<List<MotorInsurance>> getAllPolicies() {
        List<MotorInsurance> policies = motorInsuranceService.getAllPolicies();
        return new ResponseEntity<>(policies, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('USER', 'AGENT', 'ADMIN')")
    public ResponseEntity<List<MotorInsurance>> getPoliciesByUserId(@PathVariable Long userId) {
        List<MotorInsurance> policies = motorInsuranceService.getPoliciesByUserId(userId);
        return new ResponseEntity<>(policies, HttpStatus.OK);
    }

    @GetMapping("/agent/{agentId}")
    @PreAuthorize("hasAnyRole('USER', 'AGENT', 'ADMIN')")
    public ResponseEntity<List<MotorInsurance>> getPoliciesByAgentId(@PathVariable Long agentId) {
        List<MotorInsurance> policies = motorInsuranceService.getPoliciesByAgentId(agentId);
        return new ResponseEntity<>(policies, HttpStatus.OK);
    }

    @GetMapping("/{policyNumber}")
    @PreAuthorize("hasAnyRole('USER', 'AGENT', 'ADMIN')")
    public ResponseEntity<MotorInsurance> getPolicyByPolicyNumber(@PathVariable String policyNumber) {
        MotorInsurance policy = motorInsuranceService.getPolicyByPolicyNumber(policyNumber);
        return new ResponseEntity<>(policy, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'AGENT')")
    public ResponseEntity<MotorInsurance> createPolicy(@RequestBody MotorInsurance motorInsurance) {
        MotorInsurance createdPolicy = motorInsuranceService.createPolicy(motorInsurance);
        return new ResponseEntity<>(createdPolicy, HttpStatus.CREATED);
    }

    @PutMapping("/{policyNumber}")
    @PreAuthorize("hasAnyRole('USER', 'AGENT')")
    public ResponseEntity<MotorInsurance> updatePolicy(@PathVariable String policyNumber, @RequestBody MotorInsurance updatedPolicy) {
        MotorInsurance policy = motorInsuranceService.updatePolicy(policyNumber, updatedPolicy);
        if (policy != null) {
            return new ResponseEntity<>(policy, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{policyNumber}")
    @PreAuthorize("hasAnyRole('USER', 'AGENT')")
    public ResponseEntity<Void> deletePolicy(@PathVariable String policyNumber) {
        boolean isDeleted = motorInsuranceService.deletePolicy(policyNumber);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
