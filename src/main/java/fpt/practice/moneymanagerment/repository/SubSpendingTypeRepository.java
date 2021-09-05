package fpt.practice.moneymanagerment.repository;

import fpt.practice.moneymanagerment.model.SubSpendingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubSpendingTypeRepository extends JpaRepository<SubSpendingType, Long> {
}
