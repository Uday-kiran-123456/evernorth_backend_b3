package com.test.test.Controller;

import com.test.test.Entity.PolicyType;
import com.test.test.Service.PolicyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/policy-types")
public class PolicyTypeController {

    @Autowired
    private PolicyTypeService policyTypeService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PolicyType> createPolicyType(@RequestBody PolicyType policyType) {
        try {
            PolicyType createdPolicyType = policyTypeService.createOrUpdatePolicyType(policyType);
            return ResponseEntity.ok(createdPolicyType);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'AGENT', 'USER')")
    public List<PolicyType> getAllPolicyTypes() {
        return policyTypeService.getAllPolicyTypes();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'AGENT', 'USER')")
    public ResponseEntity<PolicyType> getPolicyTypeById(@PathVariable Long id) {
        return policyTypeService.getPolicyTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePolicyType(@PathVariable Long id) {
        if (policyTypeService.getPolicyTypeById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        policyTypeService.deletePolicyType(id);
        return ResponseEntity.noContent().build();
    }
}
