package com.domain.Service;

import com.domain.Entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article,Integer> {
    Boolean existsByLink(String link);
}
