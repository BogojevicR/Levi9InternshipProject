package internship.BookService.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String ADMIN = "ADMIN";
	private static  String uri = "/api/book/**";
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers(HttpMethod.GET,uri);
	}
	
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
        			.antMatchers(HttpMethod.GET,"localhost:8081/api/book").authenticated()
        			.antMatchers(HttpMethod.POST,"localhost:8081/api/book").hasAuthority(ADMIN)
        			.antMatchers(HttpMethod.PUT,"localhost:8081/api/book").hasAuthority(ADMIN)
        			.and().httpBasic().and().csrf().disable();
        

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authentication)
            throws Exception
    {
        authentication.inMemoryAuthentication().withUser("admin")
                .password(passwordEncoder().encode("123"))
                .authorities(ADMIN);
        authentication.inMemoryAuthentication()
		        .withUser("rale")
		        .password(passwordEncoder().encode("1243"))
		        .authorities("CUSTOMER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
 

}