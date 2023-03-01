package com.domain.Service;

import com.domain.Entity.Article;
import com.domain.Entity.Configuration;
import com.domain.Repository.ArticleRepository;
import com.domain.Repository.SettingsRepository;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
public class ArticleService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private SettingsRepository settingsRepository;

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

                            String articleLink = element.getElementsByTag("link").text();
                            boolean isArticleAlreadyInDB = !articleRepository.existsByLink(articleLink);

                            if (isArticleAlreadyInDB) {
                                Article article = getSingleArticle(configuration, element, articleLink);

                                articleRepository.save(article);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Problem with Feed Link: " + e);
                    }
                }

        );
    }

    private Article getSingleArticle(Configuration configuration, Element element, String articleLink) throws ParseException {
        Article article = new Article();
        article.setTitle(element.getElementsByTag("title").text());
        System.out.println(article.getTitle() + " " + stringContainsItemFromList(article.getTitle(), findTrackedWords()));
//        if (article.getTitle().contains(findTrackedWords())) {
//            sendMail();
//        }
        article.setLink(articleLink);
        article.setDescription(element.getElementsByTag("description").text());
        article.setPubDate(element.getElementsByTag("pubDate").text());
        DateFormat format = new SimpleDateFormat(configuration.getFeedDateFormat(), Locale.ENGLISH);
//                            System.out.println(configuration.getFeedName() + ": " + configuration.getFeedDateFormat());
//                            Tue, 23 Jul 2019 16:33:00 GMT, "EEE, dd MMM yyyy HH:mm:ss z"
        String stringDate = element.getElementsByTag("pubDate").text();
        Date date = format.parse(stringDate);
        article.setPubDateFormatted(date);
        article.setFeedName(configuration.getFeedName());
        return article;
    }

    private void sendMail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(settingsRepository.findEmail());

        msg.setSubject("mooncake");
        msg.setText("Chookity \n Pok");

        javaMailSender.send(msg);
    }

    private String[] findTrackedWords() {
        return settingsRepository.findTrackedWords().split(";");
    }

    public static boolean stringContainsItemFromList(String title, String[] trackedWords) {
        return Arrays.stream(trackedWords).parallel().anyMatch(
                p -> Pattern.compile(Pattern.quote(p), Pattern.CASE_INSENSITIVE)
                        .matcher(title)
                        .find()
        );
    }

    public Iterable<Article> readArticlesFromDB() {
        return articleRepository.findAll(Sort.by(Sort.Direction.DESC, "pubDateFormatted"));
    }

    public Iterable<Article> readArticlesByFeedNameFromDB(String feedName) {
        return articleRepository.findAllArticlesByFeedName(feedName);
    }


}
