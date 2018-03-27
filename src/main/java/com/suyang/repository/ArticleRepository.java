package com.suyang.repository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.suyang.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	@Query("SELECT o FROM Article o WHERE o.Title like %:title%")
	Page<Article> findByLikeTitle(@Param("title") String title, Pageable pageable);
	int countByUrl(String url);
}
