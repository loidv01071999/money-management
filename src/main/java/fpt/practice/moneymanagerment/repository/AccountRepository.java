package fpt.practice.moneymanagerment.repository;

import fpt.practice.moneymanagerment.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

//    @Query("SELECT ac FROM Account ac where ac.username=?1 AND ac.password=?2")
//    Account getAccountByUsernameAndPassword(String username, String password);
    Account findAccountByUsernameAndPassword(String username, String password);

    Account findAccountByUsername(String username);
}
