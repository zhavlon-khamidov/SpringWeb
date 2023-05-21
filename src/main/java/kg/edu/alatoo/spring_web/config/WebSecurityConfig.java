package kg.edu.alatoo.spring_web.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity security) throws Throwable {
        security
                //.csrf().disable()
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").permitAll())

                .formLogin(login ->
                        login.loginPage("/login")
                                .permitAll()
                                .usernameParameter("username")
                                .passwordParameter("password")
                )

                .authorizeHttpRequests(req ->
                        req
                                .requestMatchers("/", "/error", "/css/**", "/img/**", "/js/**","/cover/**").permitAll()
                                .requestMatchers("/login","/register").anonymous()
                                .requestMatchers("/admin").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
        ;


        return security.build();

    }


    /**
     * By default, spring looks for UserDetailsService type bean to find actual user.
     * Here I've created the bean with type {@link org.springframework.security.provisioning.InMemoryUserDetailsManager
     * InMemoryUserDetailsManager} that has single user with username 'user'.
     * <p></p>
     * Update: I'm going to create my {@link kg.edu.alatoo.spring_web.services.CustomUserService CustomUserService} that gets user from database, with using Spring Data JPA
     * So, I've disabled this {@link org.springframework.context.annotation.Bean @Bean} creation
     *
     * @see org.springframework.security.core.userdetails.UserDetailsService UserDetailsService
     */

//    @Bean
    @SuppressWarnings("deprecation")
    UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withDefaultPasswordEncoder()
                        .username("user").password("user").roles("USER").build()
        );
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
