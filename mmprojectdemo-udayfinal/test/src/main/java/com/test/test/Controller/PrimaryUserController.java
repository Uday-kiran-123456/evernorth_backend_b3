package com.test.test.Controller;

import com.test.test.DTO.CustomerDetailsResponse;
import com.test.test.Entity.*;
import com.test.test.Repository.*;
import com.test.test.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class PrimaryUserController {

    @Autowired
    private PrimaryUserService primaryUserService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ContactInformationService contactInformationService;

    @Autowired
    private ContactInformationRepository contactInformationRepository;

    @Autowired
    private DeliveryAddressService deliveryAddressService;

    @Autowired
    private DeliveryAddressRepository deliveryAddressRepository;

    @Autowired
    private HealthInformationService healthInformationService;

    @Autowired
    private HealthInformationRepository healthInformationRepository;

    @Autowired
    private PaymentInformationService paymentInformationService;

    @Autowired
    private PaymentInformationRepository paymentInformationRepository;

    @Autowired
    private SecurityInformationService securityInformationService;

    @Autowired
    private SecurityInformationRepository securityInformationRepository;

    @Autowired
    private DependentInformationService dependentInformationService;

    @Autowired
    private DependentInformationRepository dependentInformationRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/validateMembershipId/{membershipId}")
    public ResponseEntity<?> validateMembershipId(@PathVariable String membershipId) {
        boolean exists = primaryUserService.isMembershipIdExists(membershipId);
        if (exists) {
            return ResponseEntity.ok("Membership ID is valid");
        } else {
            return ResponseEntity.status(404).body("Membership ID not found");
        }
    }

    @PostMapping("/dependent-information")
    public ResponseEntity<String> saveDependentInformation(@RequestBody DependentInformation dependentInformation) {
        try {
            // Save the dependent information to the database
            dependentInformationService.saveDependentInformation(dependentInformation);
            return ResponseEntity.ok("Dependent Information saved successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to save dependent information.");
        }
    }

    @GetMapping("/dependent/{membershipId}")
    public DependentInformation getDependentInfo(@PathVariable String membershipId) {
        return dependentInformationService.getDependentInfo(membershipId);
    }

    @PutMapping("/dependent/{membershipId}")
    public ResponseEntity<DependentInformation> updateDependentInformation(
            @PathVariable String membershipId,
            @RequestBody DependentInformation dependentInformation) {

        Optional<DependentInformation> existingDependent = Optional.ofNullable(dependentInformationRepository.findByMembershipId(membershipId));

        if (existingDependent.isPresent()) {
            DependentInformation updatedDependent  = existingDependent.get();

            // Update the fields (assuming fields in your form match the entity)
            updatedDependent.setName(dependentInformation.getName());
            updatedDependent.setRelationship(dependentInformation.getRelationship());
            updatedDependent.setAge(dependentInformation.getAge());
            updatedDependent.setAddress(dependentInformation.getAddress());
            updatedDependent.setHealthCondition(dependentInformation.getHealthCondition());
            updatedDependent.setChronicIllness(dependentInformation.getChronicIllness());
            updatedDependent.setChronicIllnessDetails(dependentInformation.getChronicIllnessDetails());
            updatedDependent.setAllergies(dependentInformation.getAllergies());
            updatedDependent.setMedications(dependentInformation.getMedications());
            updatedDependent.setEmergencyContactName(dependentInformation.getEmergencyContactName());
            updatedDependent.setEmergencyContactPhone(dependentInformation.getEmergencyContactPhone());

            // Save the updated contact information
            DependentInformation savedDependent = dependentInformationRepository.save(updatedDependent);

            return ResponseEntity.ok(savedDependent);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint to save security information
    @PostMapping("/security-information")
    public String saveSecurityInformation(@RequestBody SecurityInformation securityInformation) {
        // Save the security information and hash the password
        SecurityInformation savedInfo = securityInformationService.saveSecurityInformation(securityInformation);

        // Return a success message
        return "Security information saved successfully!";
    }

    @GetMapping("/security/{membershipId}")
    public SecurityInformation getSecurityInfo(@PathVariable String membershipId) {
        return securityInformationService.getSecurityInfo(membershipId);
    }

    @PutMapping("/security/{membershipId}")
    public ResponseEntity<SecurityInformation> updateSecurityInformation(
            @PathVariable String membershipId,
            @RequestBody SecurityInformation securityInformation) {

        Optional<SecurityInformation> existingSecurity = Optional.ofNullable(securityInformationRepository.findByMembershipId(membershipId));

        if (existingSecurity.isPresent()) {
            SecurityInformation updatedSecurity = existingSecurity.get();

            // Update the fields (assuming fields in your form match the entity)
            updatedSecurity.setPassword(securityInformation.getPassword());
            updatedSecurity.setSecurityAnswer(securityInformation.getSecurityAnswer());
            updatedSecurity.setSecurityQuestion(securityInformation.getSecurityQuestion());

            // Save the updated contact information
            SecurityInformation savedSecurity = securityInformationRepository.save(updatedSecurity);

            return ResponseEntity.ok(savedSecurity);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/payment-information")
    public ResponseEntity<String> submitPaymentInformation(@RequestBody PaymentInformation paymentInformation) {
        try {
            // Save the payment information in the database
            paymentInformationService.savePaymentInformation(paymentInformation);
            return new ResponseEntity<>("Payment information submitted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error submitting payment information.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/payments/{membershipId}")
    public PaymentInformation getPaymentInfo(@PathVariable String membershipId) {
        return paymentInformationService.getPaymentInfo(membershipId);
    }

    @PutMapping("/payments/{membershipId}")
    public ResponseEntity<PaymentInformation> updatePaymentInformation(
            @PathVariable String membershipId,
            @RequestBody PaymentInformation paymentInformation) {

        Optional<PaymentInformation> existingPayment = Optional.ofNullable(paymentInformationRepository.findByMembershipId(membershipId));

        if (existingPayment.isPresent()) {
            PaymentInformation updatedPayment = existingPayment.get();

            // Update the fields (assuming fields in your form match the entity)
            updatedPayment.setCardHolderName(paymentInformation.getCardHolderName());
            updatedPayment.setCardNumber(paymentInformation.getCardNumber());
            updatedPayment.setExpirationDate(paymentInformation.getExpirationDate());
            updatedPayment.setCvv(paymentInformation.getCvv());
            updatedPayment.setDebitCardHolderName(paymentInformation.getDebitCardHolderName());
            updatedPayment.setDebitCardNumber(paymentInformation.getDebitCardNumber());
            updatedPayment.setUpiId(paymentInformation.getUpiId());

            // Save the updated contact information
            PaymentInformation savedPayment = paymentInformationRepository.save(updatedPayment);

            return ResponseEntity.ok(savedPayment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/health-information")
    public String submitHealthInformation(@RequestBody HealthInformation healthInformation) {
        if (healthInformationService.saveHealthInformation(healthInformation)) {
            return "Health information submitted successfully!";
        } else {
            return "Failed to submit health information.";
        }
    }

    @GetMapping("/healthConditions/{membershipId}")
    public HealthInformation getHealthInfo(@PathVariable String membershipId) {
        return healthInformationService.getHealthInfo(membershipId);
    }

    @PutMapping("/healthConditions/{membershipId}")
    public ResponseEntity<HealthInformation> updateHealthInformation(
            @PathVariable String membershipId,
            @RequestBody HealthInformation healthInformation) {

        Optional<HealthInformation> existingHealth = Optional.ofNullable(healthInformationRepository.findByMembershipId(membershipId));

        if (existingHealth.isPresent()) {
            HealthInformation updatedHealth = existingHealth.get();

            // Update the fields (assuming fields in your form match the entity)
            updatedHealth.setHasChronicIllness(healthInformation.getHasChronicIllness());
            updatedHealth.setChronicIllnessDetails(healthInformation.getChronicIllnessDetails());
            updatedHealth.setAllergies(healthInformation.getAllergies());
            updatedHealth.setCurrentMedications(healthInformation.getCurrentMedications());
            updatedHealth.setEmergencyContactName(healthInformation.getEmergencyContactName());
            updatedHealth.setEmergencyContactPhone(healthInformation.getEmergencyContactPhone());

            // Save the updated contact information
            HealthInformation savedHealth = healthInformationRepository.save(updatedHealth);

            return ResponseEntity.ok(savedHealth);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/delivery-submit")
    public ResponseEntity<String> submitAddress(@RequestBody DeliveryAddressInformation deliveryAddress) {
        boolean isSaved = deliveryAddressService.saveDeliveryAddress(deliveryAddress);

        if (isSaved) {
            return ResponseEntity.ok("Address submitted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to submit address.");
        }
    }

    @GetMapping("/delivery/{membershipId}")
    public DeliveryAddressInformation getDeliveryInfo(@PathVariable String membershipId) {
        return deliveryAddressService.getDeliveryAddressInfo(membershipId);
    }

    @PutMapping("/delivery/{membershipId}")
    public ResponseEntity<DeliveryAddressInformation> updateDeliveryInformation(
            @PathVariable String membershipId,
            @RequestBody DeliveryAddressInformation addressInformation) {

        Optional<DeliveryAddressInformation> existingAddress = Optional.ofNullable(deliveryAddressRepository.findByMembershipId(membershipId));

        if (existingAddress.isPresent()) {
            DeliveryAddressInformation updatedAddress = existingAddress.get();

            // Update the fields (assuming fields in your form match the entity)
            updatedAddress.setHomeNumber(addressInformation.getHomeNumber());
            updatedAddress.setStreet(addressInformation.getStreet());
            updatedAddress.setCity(addressInformation.getCity());
            updatedAddress.setState(addressInformation.getState());
            updatedAddress.setPinCode(addressInformation.getPinCode());
            updatedAddress.setCountry(addressInformation.getCountry());

            // Save the updated contact information
            DeliveryAddressInformation savedAddress = deliveryAddressRepository.save(updatedAddress);

            return ResponseEntity.ok(savedAddress);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/contact")
    public String submitContactForm(@RequestBody ContactInformation contactInformation) {
        contactInformationService.saveContactInfo(contactInformation);
        return "Contact information saved successfully!";
    }

    @GetMapping("/contact/{membershipId}")
    public ContactInformation getContactInfo(@PathVariable String membershipId) {
        return contactInformationService.getContactInfo(membershipId);
    }
    @PutMapping("/contact/{membershipId}")
    public ResponseEntity<ContactInformation> updateContactInformation(
            @PathVariable String membershipId,
            @RequestBody ContactInformation contactInformation) {

        // Find the contact information by membershipId
        Optional<ContactInformation> existingContact = Optional.ofNullable(contactInformationRepository.findByMembershipId(membershipId));

        if (existingContact.isPresent()) {
            ContactInformation updatedContact = existingContact.get();

            // Update the fields (assuming fields in your form match the entity)
            updatedContact.setFirstName(contactInformation.getFirstName());
            updatedContact.setLastName(contactInformation.getLastName());
            updatedContact.setEmail(contactInformation.getEmail());
            updatedContact.setPhone(contactInformation.getPhone());
            updatedContact.setAddress(contactInformation.getAddress());
            updatedContact.setCity(contactInformation.getCity());
            updatedContact.setState(contactInformation.getState());
            updatedContact.setZip(contactInformation.getZip());
            updatedContact.setCountry(contactInformation.getCountry());
            updatedContact.setEmergencyContactName(contactInformation.getEmergencyContactName());
            updatedContact.setEmergencyContactPhone(contactInformation.getEmergencyContactPhone());

            // Save the updated contact information
            ContactInformation savedContact = contactInformationRepository.save(updatedContact);

            return ResponseEntity.ok(savedContact);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private final Map<String, String> otpStore = new HashMap<>();
    private static final Map<String, Long> otpExpiryStore = new HashMap<>();
    private static final long OTP_EXPIRY_TIME = TimeUnit.MINUTES.toMillis(5);

    @PostMapping("/users")
    public ResponseEntity<PrimaryUser> createUser(@RequestBody PrimaryUser primaryUser) {
        PrimaryUser savedUser = primaryUserService.saveUser(primaryUser);
        String membershipId = savedUser.getMembershipId();
        String profileSetupLink = "http://localhost:5173/signuppage";
        String emailSubject = "Signup to Evernorth Health Services";
        String emailBody = "To initialize your profile setup, visit the following link:\n" + profileSetupLink;
        emailService.sendEmail(primaryUser.getEmail(), emailSubject, emailBody);
        emailService.sendSms(primaryUser.getPhone(), emailBody);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        try {
            // Generate OTP
            String otp = String.valueOf((int)(Math.random() * 9000) + 1000); // 4-digit OTP

            // Store OTP and expiry (5 minutes)
            otpStore.put(email, otp);
            otpExpiryStore.put(email, System.currentTimeMillis() + (5 * 60 * 1000));

            // Send OTP via email (use an email service here)
            emailService.sendEmail(email, "Your OTP Code", "Your OTP is: " + otp);

            return ResponseEntity.ok("OTP sent successfully to " + email);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error sending OTP: " + e.getMessage());
        }
    }


    @GetMapping("/account-creation")
    public ResponseEntity<String> requestOtp(@RequestParam String membershipid) {
        if (otpStore.containsKey(membershipid)) {
            return ResponseEntity.ok("OTP has been sent to your registered email.");
        }
        return ResponseEntity.badRequest().body("Invalid membership ID.");
    }

    @GetMapping("/customer-details")
    public ResponseEntity<?> getCustomerDetails(@RequestParam("membershipId") String membershipId) {
        try {
            Optional<Customer> customerOpt = customerRepository.findByMembershipId(membershipId);

            if (customerOpt.isPresent()) {
                Customer customer = customerOpt.get();
                // Return DTO with email and phone
                CustomerDetailsResponse response = new CustomerDetailsResponse(customer.getName(),customer.getEmail(), customer.getPhone());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(404).body("Customer not found with Membership ID: " + membershipId);
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam("membershipId") String membershipId, @RequestParam("otp") String otp) {
        try {
            // Check if OTP exists and is within expiry time
            if (otpStore.containsKey(membershipId) && otpStore.get(membershipId).equals(otp)) {
                long expiryTime = otpExpiryStore.get(membershipId);
                if (System.currentTimeMillis() <= expiryTime) {
                    // OTP is valid, proceed to profile setup completion
                    otpStore.remove(membershipId);  // Remove OTP after successful verification
                    otpExpiryStore.remove(membershipId);
                    return ResponseEntity.ok("OTP verified successfully. Proceed to complete your profile.");
                } else {
                    return ResponseEntity.status(400).body("OTP has expired.");
                }
            } else {
                return ResponseEntity.status(400).body("Invalid OTP.");
            }
        } catch (Exception e) {
            // Error handling for verification
            return ResponseEntity.status(500).body("Error occurred during OTP verification: " + e.getMessage());
        }
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<String> validateOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");
        try {
            if (otpStore.containsKey(email) && otpStore.get(email).equals(otp)) {
                long expiryTime = otpExpiryStore.get(email);
                if (System.currentTimeMillis() <= expiryTime) {
                    // OTP is valid
                    otpStore.remove(email); // Remove OTP after validation
                    otpExpiryStore.remove(email);
                    return ResponseEntity.ok("OTP validated successfully.");
                } else {
                    return ResponseEntity.status(400).body("OTP has expired.");
                }
            } else {
                return ResponseEntity.status(400).body("Invalid OTP.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error validating OTP: " + e.getMessage());
        }
    }


    private String generateOtp() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }

}

// Helper class for OTP verification request
class OtpRequest {
    private String membershipId;
    private String otp;

    // Getters and setters

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
