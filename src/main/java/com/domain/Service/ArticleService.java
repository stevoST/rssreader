package com.domain.Service;

import com.domain.Entity.Article;
import com.domain.Entity.Configuration;
import com.domain.Repository.ArticleRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class ArticleService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private MailNotification mailNotification;

    public void fetchArticles() {

        Iterable<Configuration> configurations = configurationService.readConfigurationFromDB();
        configurations.forEach(
                configuration -> {

                    try {
                        Document doc = Jsoup.connect(configuration.getFeedLink()).get();
                        Elements items = doc.getElementsByTag("item");

                        for (Element element : items) {

                            String ArticleLink = element.getElementsByTag("link").text();
                            Boolean isArticleAlreadyInDB = !articleRepository.existsByLink(ArticleLink);

                            if (isArticleAlreadyInDB) {
                                Article article = new Article();
                                article.setTitle(element.getElementsByTag("title").text());
                                if (article.getTitle().contains("Huawei")) {
                                    sendMail();
                                }
                                article.setLink(ArticleLink);
                                article.setDescription(element.getElementsByTag("description").text());
                                article.setPubDate(element.getElementsByTag("pubDate").text());
                                DateFormat format = new SimpleDateFormat(configuration.getFeedDateFormat(), Locale.ENGLISH);
//                            System.out.println(configuration.getFeedName() + ": " + configuration.getFeedDateFormat());
//                            Tue, 23 Jul 2019 16:33:00 GMT, "EEE, dd MMM yyyy HH:mm:ss z"
                                String stringDate = element.getElementsByTag("pubDate").text();
                                Date date = format.parse(stringDate);
                                article.setPubDateFormatted(date);
                                article.setFeedName(configuration.getFeedName());

                                articleRepository.save(article);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Problem with Feed Link: " + e);
                    }
                }

        );
    }

    public void sendMail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("stefan.starosta@gmail.com");

        msg.setSubject("mooncake");
        msg.setText("Chookity \n Pok");

        javaMailSender.send(msg);
    }

    public Iterable<Article> readArticlesFromDB() {
        return articleRepository.findAll(new Sort(Sort.Direction.DESC, "pubDateFormatted"));
    }

    public Iterable<Article> readArticlesByFeedNameFromDB(String feedName) {
        return articleRepository.findAllArticlesByFeedName(feedName);
    }


}
