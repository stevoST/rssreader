package com.domain.Service;

import com.domain.Entity.Article;
import com.domain.Entity.Configuration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class ArticleService {


    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ConfigurationService configurationService;

    public void fetchArticles() {

        Iterable<Configuration> configurations = configurationService.readConfigurationFromDB();
        configurations.forEach(
                configuration -> {

                    try {
                        Document doc = Jsoup.connect(configuration.getFeedLink()).get();
                        Elements items = doc.getElementsByTag("item");

                        for (Element element : items) {

                            Article article = new Article();
                            article.setTitle(element.getElementsByTag("title").text());
                            article.setLink(element.getElementsByTag("link").text());
                            article.setDescription(element.getElementsByTag("description").text());
                            article.setPubDate(element.getElementsByTag("pubDate").text());
                            DateFormat format = new SimpleDateFormat(configuration.getFeedDateFormat(), Locale.ENGLISH);
//                            System.out.println(configuration.getFeedName() + ": " + configuration.getFeedDateFormat());
//                            Tue, 23 Jul 2019 16:33:00 GMT, "EEE, dd MMM yyyy HH:mm:ss z"
                            String stringDate = element.getElementsByTag("pubDate").text();
                            Date date = format.parse(stringDate);
                            article.setPubDateFormatted(date);
                            article.setFeedName(configuration.getFeedName());

                            if (!articleRepository.existsByLink(article.getLink())) {
                                articleRepository.save(article);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Problem with Feed Link: " + e);
                    }
                }

        );
    }

    public Iterable<Article> readArticlesFromDB() {
        return articleRepository.findAll(new Sort(Sort.Direction.DESC, "pubDateFormatted" ));
    }

    public Iterable<Article> readArticlesByFeedNameFromDB(String feedName) {
        return articleRepository.findAllArticlesByFeedName(feedName);
    }


}
