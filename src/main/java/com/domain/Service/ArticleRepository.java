package com.domain.Service;

import com.domain.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Integer> {
    Boolean existsByLink(String link);

//    @Query("SELECT u FROM Article u")
//    List<Article> findAllArticles();
}
