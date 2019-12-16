package ru.kinopoisk.search.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.kinopoisk.search.service.impl.TopServiceImpl;
import java.io.IOException;

@EnableScheduling
@Component("scheduler")
public class Scheduler {

    @Autowired
    TopServiceImpl topService;

    @Scheduled(cron = "* * 11/23 * * ?")
    public void cronSchedule() throws IOException {
        topService.getTop();
    }

}
