package com.github.mgkim1.radius_account_generator.repository;

import com.github.mgkim1.radius_account_generator.entity.Radcheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RadcheckRepository extends JpaRepository<Radcheck, Integer> {

    boolean existsByUsername(String username); // 사용자 이름 중복 여부 확인을 위해 사용
}
