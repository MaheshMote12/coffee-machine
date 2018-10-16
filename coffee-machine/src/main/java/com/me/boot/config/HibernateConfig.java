package com.me.boot.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages= {"com.me.boot.model"})
@EnableJpaRepositories(basePackages= {"com.me.boot.repository"})
@EnableJpaAuditing
public class HibernateConfig {}
