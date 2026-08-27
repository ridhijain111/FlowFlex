package com.flowflex.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public final class UserRepositories { private UserRepositories() {} }
interface UserRepository extends JpaRepository<User, Long> {}
interface ProfileRepository extends JpaRepository<FinancialProfile, Long> { Optional<FinancialProfile> findByUserId(Long userId); }
interface UserLoanRepository extends JpaRepository<Loan, Long> { Optional<Loan> findByUserId(Long userId); }
interface IncomeTransactionRepository extends JpaRepository<IncomeTransaction, Long> { List<IncomeTransaction> findByUserIdOrderByTransactionDateAsc(Long userId); Optional<IncomeTransaction> findByIdAndUserId(Long id, Long userId); }
interface ContributionRepository extends JpaRepository<ContributionTransaction, Long> {
    boolean existsByUserIdAndContributionDate(Long userId, java.time.LocalDate contributionDate);
}
