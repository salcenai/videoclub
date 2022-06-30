package org.example.videoclub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder(4))
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT nombre, contrasena, activo FROM USUARIOS where nombre=?")
                .authoritiesByUsernameQuery("SELECT nombre, rol FROM USUARIOS where nombre=?")
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**", "/images/**", "/registro", "/busqueda",
                        "/usuario/nuevo",
                        "/pelicula/{id}", "/pelicula/buscarPeliculas", "/pelicula/buscarPeliculasAvanzado",
                        "/genero/{codigoGenero}", "/genero/buscarPeliculasPorGenero")
                    .permitAll()
                .antMatchers("/estado/nuevo")
                    .authenticated()
//                    .hasAuthority("USER")
                .anyRequest()
                    .hasAuthority("ADMIN")
                .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/")
                    .failureUrl("/login")
                    .usernameParameter("nombre")
                    .passwordParameter("contrasena")
                    .permitAll()
                .and()
                .logout()
                    .logoutSuccessUrl("/")
                    .permitAll();

        http.csrf().disable();

    }

}
