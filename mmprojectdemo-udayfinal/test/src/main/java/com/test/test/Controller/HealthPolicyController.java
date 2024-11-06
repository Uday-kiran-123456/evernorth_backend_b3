package com.test.test.Controller;


import com.test.test.Entity.HealthPolicy;
import com.test.test.Service.HealthPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/healthpolicies")
public class HealthPolicyController {

    @Autowired
    private HealthPolicyService healthPolicyService;

    // Get all health policies (Admin only)
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<HealthPolicy> getAllHealthPolicies() {
        return healthPolicyService.getAllHealthPolicies();
    }

    // Get policies by userId (Accessible to all authenticated users)
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public List<HealthPolicy> getPoliciesByUserId(@PathVariable Long userId) {
        return healthPolicyService.getPoliciesByUserId(userId);
    }

    // Get policies by agentId (Accessible to all authenticated users)
    @GetMapping("/agent/{agentId}")
    @PreAuthorize("hasRole('AGENT')")
    public List<HealthPolicy> getPoliciesByAgentId(@PathVariable Long agentId) {
        return healthPolicyService.getPoliciesByAgentId(agentId);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'AGENT')")
    public HealthPolicy createHealthPolicy(@RequestBody HealthPolicy healthPolicy) {
        return healthPolicyService.createHealthPolicy(healthPolicy);
    }

    @PutMapping("/update/{policyNumber}")
    @PreAuthorize("hasAnyRole('USER', 'AGENT')")
    public ResponseEntity<HealthPolicy> updatePolicy(
            @PathVariable String policyNumber,
            @RequestBody HealthPolicy updatedPolicy) {
        try {
            HealthPolicy updatedHealthPolicy = healthPolicyService.updatePolicy(policyNumber, updatedPolicy);
            return new ResponseEntity<>(updatedHealthPolicy, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Policy not found
        }
    }


    @PreAuthorize("hasAnyRole('USER', 'AGENT')")
    @DeleteMapping("/{policyNumber}")
    public ResponseEntity<Void> deletePolicy(@PathVariable String policyNumber) {
        boolean isDeleted = healthPolicyService.deletePolicy(policyNumber);

        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Policy not found
        }

        return ResponseEntity.noContent().build(); // Successfully deleted
    }

    @GetMapping("/{policyNumber}")
    @PreAuthorize("hasAnyRole('USER', 'AGENT')")
    public ResponseEntity<HealthPolicy> getHealthPolicyByPolicyNumber(@PathVariable String policyNumber) {
        HealthPolicy healthPolicy = healthPolicyService.findPolicyByNumber(policyNumber);
        if (healthPolicy != null) {
            return ResponseEntity.ok(healthPolicy);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // Or you can return an error response
        }
    }

}

