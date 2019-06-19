package com.domain.Service;

import com.domain.Entity.Article;
import com.domain.Entity.Configuration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {


    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ConfigurationService configurationService;

    public void getArticles() throws IOException {

        Iterable<Configuration> configurations = configurationService.readConfigurationFromDB();
        List<String> feedLinksList = new ArrayList<>();
        configurations.forEach(configuration -> feedLinksList.add(configuration.getFeedLink()));

        for(String feedlink : feedLinksList) {
            Document doc = Jsoup.connect(feedlink).get();
            Elements items = doc.getElementsByTag("item");

            for (Element element : items) {
                Article article = new Article();
                article.setTitle(element.getElementsByTag("title").text());
                article.setLink(element.getElementsByTag("link").text());
                article.setDescription(element.getElementsByTag("description").text());
                article.setPubDate(element.getElementsByTag("pubDate").text());
                try {
                    articleRepository.save(article);
                } catch (Exception e) {
                }
            }

        }
    }

    public Iterable<Article> readArticlesFromDB() {
        return articleRepository.findAll();

    }
}
