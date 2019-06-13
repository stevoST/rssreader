package com.domain.Controller;

import com.domain.Entity.Article;
import com.domain.Entity.Configuration;
import com.domain.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/configuration")
    public Iterable<Configuration> getConfiguration() {
//        Configuration configuration = new Configuration();
//        Configuration configuration1 = new Configuration();
//        List<Configuration> configurationList = new ArrayList<Configuration>();
//
//        configuration.setFeedName("dsl.sk");
//        configuration.setFeedLink("http://www.dsl.sk/export/rss_articles.php");
//
//        configuration1.setFeedName("zive.cz");
//        configuration1.setFeedLink("https://www.zive.cz/rss/sc-47/");
//        configurationList.add(configuration);
//        configurationList.add(configuration1);

//        return  configurationList;
        return articleService.readConfigurationFromDB();
    }

}
