package com.domain.Controller;

import com.domain.Entity.Article;
import com.domain.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Article> getArticlesFromDb() {
       return articleService.readArticlesFromDB();
    }

    @RequestMapping(value = "/fetcharticles", method = RequestMethod.GET)
    public void getArticles(){
        articleService.fetchArticles();
    }
}
