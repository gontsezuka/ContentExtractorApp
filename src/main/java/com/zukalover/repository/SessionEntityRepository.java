package com.zukalover.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zukalover.entity.SessionEntity;

@Repository
public interface SessionEntityRepository extends JpaRepository<SessionEntity, Integer> {

	SessionEntity findByusername(String username);
}
