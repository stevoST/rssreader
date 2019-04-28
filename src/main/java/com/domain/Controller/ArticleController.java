package com.domain.Controller;

import com.domain.Entity.Article;
import com.domain.Service.ArticleRepository;
import com.domain.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Article> getTitle() throws IOException {
        return articleService.getArticles();
    }

    @GetMapping(path = "/read")
    public @ResponseBody
    Optional<Article> getAllArticlesFromDb(){
        return articleRepository.findById(5);
    }
}
