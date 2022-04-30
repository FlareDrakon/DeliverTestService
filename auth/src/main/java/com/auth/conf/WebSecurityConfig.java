package com.auth.conf;

import com.auth.app.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String REALM = "someWeb";

    @Bean
    public DaoAuthenticationProvider authenticationProvider(MyUserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new PasswordEncoder() {
            PasswordEncoder delegate = new BCryptPasswordEncoder(11);

            // password -> hash
            Map<String, String> matchedCache = new ConcurrentHashMap<>();

            @Override
            public String encode(CharSequence rawPassword) {
                return delegate.encode(rawPassword);
            }

            @Override
            public boolean matches(CharSequence presentedPassword, String expectedHash) {
                String key = presentedPassword.toString();
                if (expectedHash.equals(matchedCache.get(key))) {
                    return true;
                }

                boolean matches = delegate.matches(key, expectedHash);
                if (matches) {
                    matchedCache.put(key, expectedHash);
                }
                return matches;
            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/courier/list").hasAnyRole("ADMIN")
                .antMatchers("/deliver/list").hasAnyRole("USER", "ADMIN", "COURIER")
                .antMatchers("/deliver/details").hasAnyRole("USER", "ADMIN", "COURIER")
                .antMatchers("/deliver/cancel").hasAnyRole("USER", "ADMIN")
                .antMatchers("/deliver/manage").hasAnyRole("USER", "ADMIN", "COURIER")
                .antMatchers("/deliver/create").hasAnyRole("USER", "ADMIN")
                .antMatchers("/user/add").hasAnyRole("USER", "ADMIN")
                .and().httpBasic().realmName(REALM)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.
    }

    /* To allow Pre-flight [OPTIONS] request from browser */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/user/register");
    }
}
