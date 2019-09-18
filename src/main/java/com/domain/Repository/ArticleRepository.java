package com.domain.Repository;

import com.domain.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Integer> {
    Boolean existsByLink(String link);

    @Query(value = "SELECT * FROM Article u WHERE u.feed_name = ?1 ORDER BY u.pub_date_formatted DESC", nativeQuery = true)
    List<Article> findAllArticlesByFeedName(String feedName);
}
