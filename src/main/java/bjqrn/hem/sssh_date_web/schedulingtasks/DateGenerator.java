package bjqrn.hem.sssh_date_web.schedulingtasks;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DateGenerator {

    private static final Logger log = LoggerFactory.getLogger(DateGenerator.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");

    private static String dateString;

    @Scheduled(fixedRate = 5000)
    private void generateDateString() {
        dateString = dateFormat.format(new Date());
        log.info(String.format("Date generated: %s", dateString));
    }

    public String getDate() {
        return dateString;
    }
}
