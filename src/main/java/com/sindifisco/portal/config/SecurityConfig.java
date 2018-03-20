package com.sindifisco.portal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sindifisco.portal.security.AppUserDetailsService;


@EnableWebSecurity
@ComponentScan(basePackageClasses = AppUserDetailsService.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/layout/**")
		.antMatchers("/images/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/usuarios/novo").hasRole("CADASTRAR_USUARIO")
				.antMatchers("/usuarios").hasRole("PESQUISAR_USUARIOS")
				.antMatchers("/sindicalizados/novo").hasRole("CADASTRAR_SINDICALIZADO")
				.antMatchers("/sindicalizados").hasRole("PESQUISAR_SINDICALIZADO")
				.antMatchers("/lancamentos/receitas/nova").hasRole("CADASTRAR_LANCAMENTO")
				.antMatchers("/lancamentos/receitas").hasRole("PESQUISAR_LANCAMENTO")
				.antMatchers("/lancamentos/despesas/nova").hasRole("CADASTRAR_LANCAMENTO")
				.antMatchers("/lancamentos/despesas").hasRole("PESQUISAR_LANCAMENTO")
				.antMatchers("/modospagamentos").hasRole("PESQUISAR_MODOPAGAMENTO")
				.antMatchers("/modospagamentos/novo").hasRole("CADASTRAR_MODOPAGAMENTO")
				.antMatchers("/sindicalizados").hasRole("PESQUISAR_MODOPAGAMENTO")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.and()
			.exceptionHandling()
				.accessDeniedPage("/403")
				.and()
			.sessionManagement()
				.invalidSessionUrl("/login");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
