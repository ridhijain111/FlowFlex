package com.flowflex.income;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<IncomeRecord, Long> {
    List<IncomeRecord> findAllByOrderByIncomeDateAsc();
    List<IncomeRecord> findByIncomeDateBetweenOrderByIncomeDateAsc(LocalDate from, LocalDate to);
    Optional<IncomeRecord> findTopByOrderByIncomeDateDesc();
}
