package fpt.practice.moneymanagerment.utils;

import fpt.practice.moneymanagerment.config.CommonProperties;
import fpt.practice.moneymanagerment.constant.Constants;
import fpt.practice.moneymanagerment.model.Account;
import fpt.practice.moneymanagerment.repository.AccountRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Component
@AllArgsConstructor
public class JwtUtils {

    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(JwtUtils.class);

    /**
     * AccountRepository
     */
    private AccountRepository accountRepository;

    /**
     * CommonProperties
     */
    private CommonProperties commonProperties;

    /**
     * Using to generate JWT Token
     *
     * @param authentication
     * @return
     */
    public String generateToken(Authentication authentication){
        LOG.info("Exact user principal from authentication");
        Account account = accountRepository.findAccountByUsernameAndStatus(authentication.getName(),"ACTIVE");

        //Calculate expire time
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + commonProperties.getExpiration());

        LOG.info("Start generate the jwt token with necessary information");
        String jwtToken = Jwts.builder()
                .setSubject(account.getUsername())
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .claim(Constants.CLAIM_ID, account.getId())
                .signWith(SignatureAlgorithm.HS512, commonProperties.getSecret())
                .compact();
        LOG.info("End generate the jwt token with necessary information");

        return jwtToken;
    }
    /**
     * Using to exact username from JWT token
     *
     * @param token
     * @return
     */
    public String getUsername(String token){
        LOG.info("Start to exact username from jwt web token string");
        String username = Jwts.parser()
                .setSigningKey(commonProperties.getSecret())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        LOG.info("End to exact username from jwt web token string");
        return username;
    }

    /**
     * Using to exact username from JWT token
     *
     * @param token
     * @return
     */
    public String getEmail(String token){
        LOG.info("Start to exact username from jwt web token string");
        String username = Jwts.parser()
                .setSigningKey(commonProperties.getSecret())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        LOG.info("End to exact username from jwt web token string");
        return username;
    }

    /**
     * Using to exact username from httpServletRequest
     *
     * @param httpServletRequest
     * @return
     */
    public Long getAccountId(HttpServletRequest httpServletRequest){
        LOG.info("Start to parse jwt token from request");
        String token = parseJwt(httpServletRequest);
        LOG.info("End parse jwt token from request");

        LOG.info("Start to exact userId from jwt web token string");
        String accountId = Jwts.parser()
                .setSigningKey(commonProperties.getSecret())
                .parseClaimsJws(token)
                .getBody()
                .get(Constants.CLAIM_ID)
                .toString();
        LOG.info("End to exact userId from jwt web token string");
        return Long.parseLong(accountId);
    }

    /**
     * Validate JWT Token
     *
     * @param authToken
     * @return
     */
    public boolean validateToken(String authToken) {
        try {
            LOG.info("Start to validate json web token string");
            Jwts.parser()
                    .setSigningKey(commonProperties.getSecret())
                    .parseClaimsJws(authToken);
            LOG.info("Json web token string validated");
            return true;
        } catch (SignatureException e) {
            LOG.error("Invalid JWT signature: ", e.getMessage());
        } catch (MalformedJwtException e) {
            LOG.error("Invalid JWT token: ", e.getMessage());
        } catch (ExpiredJwtException e) {
            LOG.error("JWT token is expired: ", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOG.error("JWT token is unsupported: ", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOG.error("JWT claims string is empty: ", e.getMessage());
        }

        return false;
    }

    /**
     * parse jwt from HttpServletRequest
     *
     * @param request
     * @return
     */
    public String parseJwt(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        // Checking whether the request has JWT token
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
