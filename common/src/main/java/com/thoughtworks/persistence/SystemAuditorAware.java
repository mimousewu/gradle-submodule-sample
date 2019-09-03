package com.thoughtworks.persistence;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;


public class SystemAuditorAware implements AuditorAware<String> {

    private static final String USERNAME_SYS = "SYSTEM";

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(USERNAME_SYS);
    }
}
