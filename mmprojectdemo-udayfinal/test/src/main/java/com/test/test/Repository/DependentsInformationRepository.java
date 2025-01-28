package com.test.test.Repository;

import com.test.test.Entity.DeliveryAddressInformation;
import com.test.test.Entity.DependentsInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DependentsInformationRepository extends JpaRepository<DependentsInformation, Long> {
    boolean existsByMembershipId(String membershipId);
    DependentsInformation findByMembershipId(String membershipId);
}
