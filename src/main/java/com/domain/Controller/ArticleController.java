package com.domain.Controller;

import com.domain.Entity.Article;
import com.domain.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value ="/{feedName}", method = RequestMethod.GET)
    public Iterable<Article> getArticlesByFeedName(@PathVariable("feedName") String feedName){
        return articleService.readArticlesByFeedNameFromDB(feedName);
    }
}
