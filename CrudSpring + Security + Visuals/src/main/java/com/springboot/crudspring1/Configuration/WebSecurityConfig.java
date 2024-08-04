package com.springboot.crudspring1.Configuration;

import com.springboot.crudspring1.Model.Pessoa;
import com.springboot.crudspring1.Repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public WebSecurityConfig(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/index", "/pessoa/listar").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
                Pessoa pessoa = pessoaRepository.findByNome(nome)
                        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + nome));

                return User.withDefaultPasswordEncoder()
                        .username(pessoa.getNome())
                        .password(pessoa.getSenha())
                        .roles("USER")
                        .build();
            }
        };
    }
}
