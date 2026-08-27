package com.flowflex.config;

import com.flowflex.income.IncomeRecord;
import com.flowflex.income.IncomeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class SeedData {
    @Bean
    CommandLineRunner seedIncome(IncomeRepository repository) {
        return args -> {
            if (repository.count() > 0) return;
            double[] amounts = {1800, 2250, 0, 1650, 2900, 2100, 1350, 2450, 1900, 3200, 450, 1750, 2050, 0, 2600, 2300, 1850, 2750, 1500, 3400, 2200, 1950, 2800, 1100, 2500, 1800, 3100, 2000, 900, 650, 500, 0, 700, 400, 450};
            LocalDate start = LocalDate.now().minusDays(amounts.length - 1L);
            for (int i = 0; i < amounts.length; i++) repository.save(new IncomeRecord(start.plusDays(i), amounts[i]));
        };
    }
}
