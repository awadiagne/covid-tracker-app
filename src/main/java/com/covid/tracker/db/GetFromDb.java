package com.covid.tracker.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*@Configuration
public class GetFromDb {

        private static final Logger log = LoggerFactory.getLogger(GetFromDb.class);

        @Bean
        CommandLineRunner initDatabase(PatientRepository repository) {

            return args -> {
                log.info("Preloading " + repository.save(new Patient("Bilbo Baggins", "burglar")));
                log.info("Preloading " + repository.save(new Patient("Frodo Baggins", "thief")));
            };
        }
    }


}*/
