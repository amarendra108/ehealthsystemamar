package nirmalya.aatithya.restmodule.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import nirmalya.aatithya.restmodule.security.config.JwtAuthenticationEntryPoint;
import nirmalya.aatithya.restmodule.security.config.JwtRequestFilter;

/**
 * @author Jinesh
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
  
	 @Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		httpSecurity.csrf().disable()
				// dont authenticate this particular request
				.authorizeRequests()
				//.antMatchers("/**").permitAll()
				.antMatchers("/authenticate").permitAll()
				.antMatchers("/document/**").permitAll()
				.antMatchers("/user/**").permitAll()
				.antMatchers("/doctor/**").permitAll()
				.antMatchers("/lab/**").permitAll()
				.antMatchers("/reception/**").permitAll()
				.antMatchers("/chemist/**").permitAll()
				.antMatchers("/ambulance/**").permitAll()
				.antMatchers("/bloodbank/**").permitAll()
				.antMatchers("/pm/**").permitAll()
				/*.antMatchers("/api/login").permitAll()
				.antMatchers("/api/get-user-title-list").permitAll()
				.antMatchers("/api/get-gender-list").permitAll()
				.antMatchers("/api/get-country-list").permitAll()
				.antMatchers("/api/get-district-list").permitAll()
				.antMatchers("/api/get-city-list").permitAll()
				.antMatchers("/api/get-hospital-list").permitAll()
				.antMatchers("/api/get-bloodgroup-list").permitAll()
				.antMatchers("/api/get-speciality-list").permitAll()
				.antMatchers("/api/get-relation-list").permitAll()
				.antMatchers("/api/get-state-list").permitAll()
				.antMatchers("/api/get-hospital-list").permitAll()
				.antMatchers("/api/get-doctor-list").permitAll()
				.antMatchers("/api/get-all-organization-list").permitAll()
				.antMatchers("/api/medtel-screening-test-report").permitAll()
				.antMatchers("/api/user-self-registration").permitAll()
				.antMatchers("/api/doctor-registration-details").permitAll()
				.antMatchers("/api/get-medicine-list-with-type").permitAll()
				.antMatchers("/swagger-ui.html").permitAll() */
				
				
				.antMatchers("/api/login").permitAll()
				.antMatchers("/api/get-user-title-list").permitAll()
				.antMatchers("/api/get-gender-list").permitAll()
				.antMatchers("/api/get-country-list").permitAll()
				.antMatchers("/api/get-district-list").permitAll()
				.antMatchers("/api/get-city-list").permitAll()
				.antMatchers("/api/get-hospital-list").permitAll()
				.antMatchers("/api/get-bloodgroup-list").permitAll()
				.antMatchers("/api/get-speciality-list").permitAll()
				.antMatchers("/api/get-health-provider-list").permitAll()
				.antMatchers("/api/get-relation-list").permitAll()
				.antMatchers("/api/get-state-list").permitAll()
				.antMatchers("/api/get-hospital-list").permitAll()
				.antMatchers("/api/get-doctor-list").permitAll()
				.antMatchers("/api/get-pharmacy-list").permitAll()
				.antMatchers("/api/get-all-organization-list").permitAll()
				.antMatchers("/api/medtel-screening-test-report").permitAll()
				.antMatchers("/api/user-self-registration").permitAll()
				.antMatchers("/api/doctor-registration-details").permitAll()
				.antMatchers("/api/get-medicine-list-with-type").permitAll()
				.antMatchers("/api/post-user-medication-doctor").permitAll()
				.antMatchers("/api/post-pharmacy-registration").permitAll()
				.antMatchers("/api/get-pharmacy-list-by-location").permitAll()
				.antMatchers("/api/save-screen-test-report").permitAll()
				.antMatchers("/api/system-screening-test-report").permitAll()
				.antMatchers("/swagger-ui.html").permitAll()
				
				.antMatchers("/api/get-doctor-speciality-list").permitAll()
				.antMatchers("/api/get-pharmacy-org-list").permitAll()
				.antMatchers("/api/get-ambulance-org-list").permitAll()
				.antMatchers("/api/get-bloodbank-org-list").permitAll()
				.antMatchers("/api/get-ngo-org-list").permitAll()
				
				.antMatchers("/api/get-adm-equipment-list").permitAll() 
				.antMatchers("/api/get-allergy-name-list").permitAll()
				.antMatchers("/api/get-allergy-type-list").permitAll()
				
				// 02-10-2021
				.antMatchers("/api/geo-user-registration").permitAll()
				.antMatchers("/api/geo-post-doctor-appointment").permitAll()
				.antMatchers("/api/geo-update-user-appointment-status").permitAll()
				.antMatchers("/api/geo-post-user-medication-doctor").permitAll()
				.antMatchers("/api/geo-post-user-test-by-doctor").permitAll()
				.antMatchers("/api/geo-post-user-pharmacy-request").permitAll()
				
				.antMatchers("/api/view-user-medicine-details-by-appno").permitAll()
				.antMatchers("/api/view-user-test-details-by-appno").permitAll()
				.antMatchers("/api/login-with-otp").permitAll()
				.antMatchers("/api/get-lab-list-by-location").permitAll()
				.antMatchers("/api/get-testname-list").permitAll()
				
				.antMatchers("/api/get-organ-list").permitAll()
				.antMatchers("/api/get-tissue-list").permitAll()
				
				.antMatchers("/api/forgot-password-get-otp").permitAll()
				.antMatchers("/api/change-password").permitAll()
				
				// all other requests need to be authenticated 
				.anyRequest().authenticated().and().
				// make sure we use stateless session; session won't be used to
				// store user's state.
				exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
   

	/**@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/**").permitAll()
		.antMatchers("/swagger-ui.html").permitAll()
		;
	}*/

}