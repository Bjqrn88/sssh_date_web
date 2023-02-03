package bjqrn.hem.sssh_date_web.restservice.date;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import bjqrn.hem.sssh_date_web.schedulingtasks.DateGenerator;
import bjqrn.hem.sssh_date_web.util.EncryptionUtil;

@RestController
public class DateController {

    private static final Logger log = LoggerFactory.getLogger(DateController.class);

    private final DateGenerator dateGenerator;

    private final EncryptionUtil encryptionUtil;

    @Autowired
    public DateController(DateGenerator dateGenerator, EncryptionUtil encryptionUtil) {
        this.dateGenerator = dateGenerator;
        this.encryptionUtil = encryptionUtil;
    }

    @GetMapping("/date")
    public DateResponse getDate() {
        String date = dateGenerator.getDate();
        try {
            byte[] encoded = encryptionUtil.encrypString(date);
            String decoded = encryptionUtil.decrypString(encoded);
            return new DateResponse(decoded);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
