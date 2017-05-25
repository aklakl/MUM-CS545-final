package edu.mum.coffee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers(AuthoritiesConfiguration.ANONYMOUS).permitAll()
                .antMatchers(AuthoritiesConfiguration.ADMIN_AUTHORITIES)
                	.hasRole("ADMIN")
                .antMatchers(AuthoritiesConfiguration.USER_AUTHORITIES)
                	.hasRole("USER")
                .anyRequest().authenticated()
            .and()
                .formLogin()
            	.loginPage("/login")
            	.defaultSuccessUrl("/home")
            	.failureUrl("/login?error")
            	.usernameParameter("username")
            	.passwordParameter("password")
            	.permitAll()
        	.and()
            	.logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            	.logoutSuccessUrl("/home")
            	.invalidateHttpSession(true)
            	.permitAll()
            .and()
            	.exceptionHandling()
            	.accessDeniedPage("/403");
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}