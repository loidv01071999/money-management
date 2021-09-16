package fpt.practice.moneymanagerment.security;

import fpt.practice.moneymanagerment.model.Account;
import fpt.practice.moneymanagerment.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private AccountRepository accountRepository;

    /**
     * Default method using to validate username and password of spring security
     *
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByUsernameAndStatus(username, "ACTIVE");
        if (Objects.isNull(account)) {
            throw new UsernameNotFoundException("Not found any user with given username" + username);
        }
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(account.getRole());
        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }

}
