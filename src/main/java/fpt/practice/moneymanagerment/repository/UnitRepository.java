package fpt.practice.moneymanagerment.repository;

import fpt.practice.moneymanagerment.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
}
