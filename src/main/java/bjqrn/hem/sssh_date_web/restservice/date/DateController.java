package bjqrn.hem.sssh_date_web.restservice.date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import bjqrn.hem.sssh_date_web.schedulingtasks.DateGenerator;

@RestController
public class DateController {

    private final DateGenerator dateGenerator;

    @Autowired
    public DateController(DateGenerator dateGenerator) {
        this.dateGenerator = dateGenerator;
    }

    @GetMapping("/date")
    public DateResponse getDate() {
        return new DateResponse(dateGenerator.getDate());
    }
}
