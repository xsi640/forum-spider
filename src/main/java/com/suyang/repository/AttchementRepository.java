package com.suyang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suyang.domain.Attchement;

public interface AttchementRepository extends JpaRepository<Attchement, Long> {
	List<Attchement> findByArticleId(long articleId);
}
