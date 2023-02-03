package bjqrn.hem.sssh_date_web.schedulingtasks;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DateGenerator {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");

    private static String dateString;

    @Scheduled(fixedRate = 5000)
    private void generateDateString() {
        dateString = dateFormat.format(new Date());
    }

    public String getDate() {
        return dateString;
    }
}
