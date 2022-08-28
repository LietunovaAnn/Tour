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


@EnableWebSecurity(debug = true)
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

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//       http
//                .csrf()
//                .disable()
//                .authorizeRequests()
////               .anyRequest().authenticated()
////               .and()
////               .formLogin()
////               .and()
////               .httpBasic();
////                //Доступ только для не зарегистрированных пользователей
//                .antMatchers("/registration").not().fullyAuthenticated()
////                //Доступ только для пользователей с ролью Администратор
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/news").hasRole("USER")
////                //Доступ разрешен всем пользователей
////                .antMatchers("/", "/resources/**").permitAll()
////                //Все остальные страницы требуют аутентификации
////                .anyRequest().authenticated()
//                .and()
////                //Настройка для входа в систему 2 svou log
//                .formLogin();
////                .loginPage("/login")
////                //Перенарпавление на главную страницу после успешного входа
////                .defaultSuccessUrl("/")
////                .permitAll()
////                .and()
////                .logout()
////                .permitAll()
////                .logoutSuccessUrl("/");
//    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}admin") //bcrypt
                .roles(Role.ADMIN.name());
    }
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User.builder()
//                        .username("admin")
//                        .password(passwordEncoder().encode("12345"))
//                        .roles(Role.ADMIN.name())
//                        .build()
//        );
//    }

    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
