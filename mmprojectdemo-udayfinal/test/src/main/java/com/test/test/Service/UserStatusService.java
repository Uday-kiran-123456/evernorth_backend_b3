package com.test.test.Service;

import com.test.test.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserStatusService {

    @Autowired
    private ContactInformationRepository contactInformationRepository;

    @Autowired
    private DeliveryAddressRepository deliveryAddressRepository;

    @Autowired
    private DependentInformationRepository dependentInformationRepository;

    @Autowired
    private HealthInformationRepository healthInformationRepository;

    @Autowired
    private PaymentInformationRepository paymentInformationRepository;

    @Autowired
    private SecurityInformationRepository securityInformationRepository;

    public Map<String, Boolean> getUserStatus(String membershipId) {
        Map<String, Boolean> statusMap = new HashMap<>();

        statusMap.put("contactInformation", contactInformationRepository.existsByMembershipId(membershipId));
        statusMap.put("deliveryAddressInformation", deliveryAddressRepository.existsByMembershipId(membershipId));
        statusMap.put("dependentInformation", dependentInformationRepository.existsByMembershipId(membershipId));
        statusMap.put("healthInformation", healthInformationRepository.existsByMembershipId(membershipId));
        statusMap.put("paymentInformation", paymentInformationRepository.existsByMembershipId(membershipId));
        statusMap.put("securityInformation", securityInformationRepository.existsByMembershipId(membershipId));

        return statusMap;
    }
}
