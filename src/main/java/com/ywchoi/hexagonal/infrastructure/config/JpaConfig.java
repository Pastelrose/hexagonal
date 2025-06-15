package com.ywchoi.hexagonal.infrastructure.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.ywchoi.hexagonal.adapter.out.persistence")
@EntityScan(basePackages = "com.ywchoi.hexagonal.adapter.out.persistence")
public class JpaConfig {
}