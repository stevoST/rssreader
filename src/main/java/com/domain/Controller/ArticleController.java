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
@RequestMapping("/")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Article> getArticles() {
       return articleService.readArticlesFromDB();
    }

//    @GetMapping(path = "/read")
//    public @ResponseBody
//    Optional<Article> getAllArticlesFromDb(){
//        return articleRepository.findById(5);
//    }
}
