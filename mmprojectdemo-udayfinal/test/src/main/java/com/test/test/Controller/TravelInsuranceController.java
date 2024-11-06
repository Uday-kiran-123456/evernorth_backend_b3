package com.test.test.Controller;

import com.test.test.Entity.TravelInsurance;
import com.test.test.Service.TravelInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/travelinsurances")
public class TravelInsuranceController {

    @Autowired
    private TravelInsuranceService travelInsuranceService;


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<TravelInsurance> getAllTravelPolicies() {
        return travelInsuranceService.getAllTravelPolicies();
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public List<TravelInsurance> getPoliciesByUserId(@PathVariable Long userId) {
        return travelInsuranceService.getTravelPoliciesByUserId(userId);
    }

    @GetMapping("/agent/{agentId}")
    @PreAuthorize("hasRole('AGENT')")
    public List<TravelInsurance> getPoliciesByAgentId(@PathVariable Long agentId) {
        return travelInsuranceService.getTravelPoliciesByAgentId(agentId);
    }

    @GetMapping("/policy/{policyNumber}")
    @PreAuthorize("hasAnyRole('USER', 'AGENT', 'ADMIN')")
    public TravelInsurance getPolicyByNumber(@PathVariable String policyNumber) {
        return travelInsuranceService.getPolicyByNumber(policyNumber);
    }


    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'AGENT')")
    public TravelInsurance createTravelPolicy(@RequestBody TravelInsurance travelInsurance) {
        return travelInsuranceService.createTravelPolicy(travelInsurance);
    }


    @PutMapping("/{policyNumber}")
    @PreAuthorize("hasAnyRole('USER', 'AGENT')")
    public ResponseEntity<TravelInsurance> updateTravelPolicy(
            @PathVariable String policyNumber,
            @RequestBody TravelInsurance updatedTravelInsurance) {
        TravelInsurance updatedPolicy = travelInsuranceService.updateTravelPolicy(policyNumber, updatedTravelInsurance);
        if (updatedPolicy != null) {
            return new ResponseEntity<>(updatedPolicy, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Policy not found
    }


    @DeleteMapping("/{policyNumber}")
    @PreAuthorize("hasAnyRole('USER', 'AGENT')")
    public void deleteTravelPolicy(@PathVariable String policyNumber) {
        travelInsuranceService.deleteTravelPolicy(policyNumber);
    }
}
