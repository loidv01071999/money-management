package fpt.practice.moneymanagerment.repository;

import fpt.practice.moneymanagerment.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByUsernameAndPassword(String username, String password);

    Account findAccountByUsername(String username);

    Account findAccountByEmail(String email);

    Account findAccountByUsernameAndStatus(String username, String status);
}
