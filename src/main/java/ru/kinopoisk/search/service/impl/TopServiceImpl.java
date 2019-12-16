package ru.kinopoisk.search.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kinopoisk.search.model.Top;
import ru.kinopoisk.search.repository.TopRepository;
import ru.kinopoisk.search.repository.impl.TopRepo;
import ru.kinopoisk.search.service.TopService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class TopServiceImpl implements TopService {

    @Autowired
    TopRepository topRepository;

    @Autowired
    TopRepo topRepo;

    @Autowired
    CacheManager cacheManager;

    @Cacheable("date")
    public List<Top> getTopTenByDate(LocalDate date) {
        return topRepo.getTopTenByDate(date);
    }

    public void getTop() throws IOException {
        Document doc = Jsoup.connect("http://www.kinopoisk.ru/top/").get();
        Element table = doc.select("table.js-rum-hero").first();

        //Get votes
        Elements aVotesElements = table.getElementsByTag("span");
        List<Integer> votesList = new ArrayList<>();
        for (Element a : aVotesElements) {
            if (a.attr("style").length() > 0) {
                String repl = a.text().replaceAll("[^0-9]+", "");
                votesList.add(Integer.parseInt(repl));
            }
        }

        int position = 0;
        String movieTitle = null;
        int yearOut = 0;
        float rating = 0.0f;
        boolean flag = true;
        int count = 0;
        LocalDate date = LocalDate.now();
        List<Top> topList = new ArrayList<>();
        Elements elementsByTagA = table.getElementsByTag("a");
        for (Element a : elementsByTagA) {

            //Get position
            if (a.attr("name").length() > 0) {
                position = Integer.parseInt(a.attr("name"));
                continue;
            }

            if (a.text().length() > 0 && flag) {
                //Get name movie without a date
                String title = a.text();
                movieTitle = title.substring(0, title.length() - 7);

                //Get year
                String pattern = "\\d{4}\\)$";
                Pattern p = Pattern.compile(pattern);
                Matcher m = p.matcher(title);
                while (m.find()) {
                    String yearTemp = m.group();
                    yearOut = Integer.parseInt(yearTemp.substring(0, yearTemp.length() - 1));
                }
                flag = false;
                continue;
            }

            //Get rating
            if (a.text().length() > 0 && !flag) {
                rating = Float.parseFloat(a.text());
                flag = true;
            }

            topList.add(new Top(position, movieTitle, yearOut, rating, votesList.get(count), date));
            count++;
        }
        topRepository.saveAll(topList);
    }

}
