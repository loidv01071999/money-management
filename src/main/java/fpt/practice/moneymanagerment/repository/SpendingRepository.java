package fpt.practice.moneymanagerment.repository;

import fpt.practice.moneymanagerment.model.Spending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpendingRepository extends JpaRepository<Spending, Long> {
}
