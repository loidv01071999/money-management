package fpt.practice.moneymanagerment.config;

import fpt.practice.moneymanagerment.constant.Constants;
import fpt.practice.moneymanagerment.service.impl.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Configuration
public class CommonConfiguration {

    /**
     * Create Password Encoder bean using to encrypt, decrypt password
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * Using to get username and auto set to createBy and updateBy in baseEntity
     *
     * @return AuditorAware<String>
     */
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }

    /**
     * Makes ZonedDateTime compatible with auditing fields
     *
     * @return DateTimeProvider
     */
    @Bean
    public DateTimeProvider auditingDateTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now(ZoneId.of(Constants.INDOCHINA_ZONE)));
    }
}
