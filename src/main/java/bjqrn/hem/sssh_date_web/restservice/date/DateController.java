package bjqrn.hem.sssh_date_web.restservice.date;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // Handle incomming GET requests for /date
    @GetMapping("/date")
    @CrossOrigin(origins = "*")
    public DateResponse getDate() throws UnsupportedEncodingException {
        // Get the date from DateGenerator
        String date = dateGenerator.getDate();
        // Encrypt the date using EncryptionUtil
        String encrypted = encryptionUtil.encryptString_g(date);
        // Log the date in clear text and encrypted
        log.info(String.format("Get date - Date: %s, Encrypted: %s", date, encrypted));
        return new DateResponse(encrypted);
    }
}
