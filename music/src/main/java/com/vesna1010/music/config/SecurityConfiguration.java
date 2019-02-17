package com.vesna1010.music.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.vesna1010.music.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userDetailsService)
		       .passwordEncoder(passwordEncoder);
	}

	@Override
	public void configure(WebSecurity security) throws Exception {
		security.ignoring().antMatchers("/static/**", "/webjars/**");
	}

	@Override
	protected void configure(HttpSecurity security) throws Exception {
		security.formLogin()
		            .loginPage("/login")
		            .loginProcessingUrl("/login")
		            .failureUrl("/login?error")
				    .defaultSuccessUrl("/")
				    .usernameParameter("email")
				    .passwordParameter("password")
				.and()
				.logout()
				    .logoutUrl("/logout")
				    .logoutSuccessUrl("/login")
				.and()
				.exceptionHandling()
                    .accessDeniedPage("/denied");
	}

}
