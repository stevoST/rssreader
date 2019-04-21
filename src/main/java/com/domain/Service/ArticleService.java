package com.domain.Service;

import com.domain.Entity.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    public List<Article> getArticles() throws IOException {
        List<Article> articleList = new ArrayList();

        Document doc = Jsoup.connect("http://www.dsl.sk/export/rss_articles.php").get();
        Elements items = doc.getElementsByTag("item");

        for (Element element : items) {
            Article article = new Article();
            article.setTitle(element.getElementsByTag("title").text());
            article.setLink(items.first().getElementsByTag("link").text());
            article.setDescription(items.first().getElementsByTag("description").text());
            article.setPubDate(items.first().getElementsByTag("pubDate").text());
            articleList.add(article);
        }
        return articleList;
    }
}
