package studio.lineage2.cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import studio.lineage2.cms.service.MAccountService;


@Configuration
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final MAccountService mAccountService;

  public SecurityConfig(MAccountService mAccountService) {
    this.mAccountService = mAccountService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.
      csrf().disable().
      authorizeRequests().
      antMatchers("/", "/page/*").permitAll().
      antMatchers("/**/css/**", "/**/fonts/**", "/**/js/**", "/**/images/**", "/**/img/**").permitAll().
      antMatchers("/image/**").permitAll().
      antMatchers("/pay/check*", "/api/*").permitAll().
      antMatchers("/XmlRpcGame/getTopPvP", "/XmlRpcGame/getTopPK").permitAll().
      antMatchers("/enter", "/enter/reg", "/enter/auth", "/enter/recover").permitAll().
      antMatchers("/admin/**").hasRole("ADMIN").
      anyRequest().hasRole("USER").and().
      formLogin().loginPage("/enter");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(mAccountService::findByUsername).passwordEncoder(new BCryptPasswordEncoder());
  }

  @Bean
  public AuthenticationManager customAuthenticationManager() throws Exception {
    return authenticationManager();
  }
}
