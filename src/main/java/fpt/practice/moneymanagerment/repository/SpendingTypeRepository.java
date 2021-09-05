package fpt.practice.moneymanagerment.repository;

import fpt.practice.moneymanagerment.model.SpendingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpendingTypeRepository extends JpaRepository<SpendingType, Long> {
}
