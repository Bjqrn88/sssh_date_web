package bjqrn.hem.sssh_date_web.schedulingtasks;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DateGenerator {

    // Dateformatter for the date
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");

    // Date
    private static String dateString;

    // A scheduled call to update dateString every 60000milliseconds / every minut
    @Scheduled(fixedRate = 60000)
    private void generateDateString() {
        dateString = dateFormat.format(new Date());
    }

    // Get date
    public String getDate() {
        return dateString;
    }
}
