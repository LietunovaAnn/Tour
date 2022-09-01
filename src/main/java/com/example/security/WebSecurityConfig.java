package com.example.security;

import com.example.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/index", "/").permitAll()
                .antMatchers("/addComplexity").hasRole(Role.ADMIN.name())
                .antMatchers("/editComplexity/{id}").hasRole(Role.ADMIN.name())
                .antMatchers("/removeComplexity/{id}").hasRole(Role.ADMIN.name())
                .antMatchers("customer/addCustomer").hasRole(Role.ADMIN.name())
                .antMatchers("customer/editCustomer/{id}").hasRole(Role.ADMIN.name())
                .antMatchers("customer/removeCustomer/{id}").hasRole(Role.ADMIN.name())
                .antMatchers("/addDiscount").hasRole(Role.ADMIN.name())
                .antMatchers("/editDiscount/{id}").hasRole(Role.ADMIN.name())
                .antMatchers("/removeDiscount/{id}").hasRole(Role.ADMIN.name())
                .antMatchers("tour/addTour").hasRole(Role.ADMIN.name())
                .antMatchers("tour/editTour/{id}").hasRole(Role.ADMIN.name())
                .antMatchers("tour/removeTour/{id}").hasRole(Role.ADMIN.name())
                .antMatchers("/addTypeOfTour").hasRole(Role.ADMIN.name())
                .antMatchers("/editTypeOfTour/{id}").hasRole(Role.ADMIN.name())
                .antMatchers("/removeTypeOfTour/{id}").hasRole(Role.ADMIN.name())
                .antMatchers("/editOrder/{id}").hasRole(Role.ADMIN.name())
                .antMatchers("/removeOrder/{id}").hasRole(Role.ADMIN.name())
                .antMatchers("/viewAllCustomersAndOrders").hasRole(Role.ADMIN.name())

                .and()
                .formLogin()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}admin") //bcrypt
                .roles(Role.ADMIN.name());
    }

    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
