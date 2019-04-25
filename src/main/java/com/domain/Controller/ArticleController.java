package com.domain.Controller;

import com.domain.Entity.Article;
import com.domain.Service.ArticleRepository;
import com.domain.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Article> getTitle() throws IOException {
        return articleService.getArticles();
    }
}
