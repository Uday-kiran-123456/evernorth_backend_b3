package com.test.test.Controller;


import com.test.test.Entity.Claim;
import com.test.test.Service.ClaimService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    // Create a new claim
    @PostMapping
    public ResponseEntity<Claim> createClaim(@RequestBody Claim claim) {
        Claim createdClaim = claimService.saveClaim(claim);
        return ResponseEntity.status(201).body(createdClaim);
    }

    // Get all claims
    @GetMapping
    public ResponseEntity<List<Claim>> getAllClaims() {
        List<Claim> claims = claimService.getAllClaims();
        return ResponseEntity.ok(claims);
    }

    // Get claim by ID
    @GetMapping("/{id}")
    public ResponseEntity<Claim> getClaimById(@PathVariable Long id) {
        Claim claim = claimService.getClaimById(id);
        if (claim != null) {
            return ResponseEntity.ok(claim);
        }
        return ResponseEntity.notFound().build();
    }

    // Update a claim
    @PutMapping("/{id}")
    public ResponseEntity<Claim> updateClaim(@PathVariable Long id, @RequestBody Claim claim) {
        Claim updatedClaim = claimService.updateClaim(id, claim);
        if (updatedClaim != null) {
            return ResponseEntity.ok(updatedClaim);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a claim
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClaim(@PathVariable Long id) {
        if (claimService.deleteClaim(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Claim>> getClaimsByUserId(@PathVariable Long userId) {
        List<Claim> claims = claimService.getClaimsByUserId(userId);
        return ResponseEntity.ok(claims);
    }

    // Get claims by agent ID
    @GetMapping("/agent/{agentId}")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<List<Claim>> getClaimsByAgentId(@PathVariable Long agentId) {
        List<Claim> claims = claimService.getClaimsByAgentId(agentId);
        return ResponseEntity.ok(claims);
    }

}
