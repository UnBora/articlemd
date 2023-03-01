package com.example.ariclemanagement002.repository;

import com.example.ariclemanagement002.model.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Integer countAllByStatusIs(Integer a);

    List<Article> findAllByStatus(int status, Pageable pageable);
}
