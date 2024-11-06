package com.test.test.Repository;


import com.test.test.Entity.PolicyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyTypeRepository extends JpaRepository<PolicyType, Long> {
}
