package com.example.vendas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.PasswordAuthentication;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {//vai criptografar as senhas dos usuarios dando mais segurança
            return new BCryptPasswordEncoder(); //toda vez que o usuario passa senha ele criptografa e gera um hash, o hash que ele gera é diferente

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
            .passwordEncoder(passwordEncoder())
            .withUser("Fulano") //criamos um usuario qualquer em memoria
            .password(passwordEncoder().encode("123")) //senha qualquer criada, chamamos o passwordencoder para criptografar
            .roles("USER"); //roles é como se fosse o perfil de um usuario
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable() //se trabalhar com aplicação web csrf teria que estar habilitado, se for só api de back end teria que deixa como disable
            .authorizeRequests()
            .antMatchers("/api/clientes/**")//definimos quem acessa o que
            .authenticated()
            .and() //volta para a raiz do objeto, quando chamamos a api de clientes ele vai voltando outros objetos
            .formLogin(); //cria formulario de login do spring security, ou posso criar um formulario de login mesmo e adicionar o caminho lá. Como usaremos do spring vai ficar sem caminho
    }
}
