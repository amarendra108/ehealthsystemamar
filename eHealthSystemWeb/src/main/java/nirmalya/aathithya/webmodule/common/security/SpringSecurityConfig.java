/**
 *  Configures Web Security
 */
package nirmalya.aathithya.webmodule.common.security;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import nirmalya.aathithya.webmodule.user.service.CustomAuthenticationSuccessHandler;
import nirmalya.aathithya.webmodule.user.service.CustomUserDetailsService;

/**
 * @author Nirmalya Labs
 *
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Autowired
	HttpSession session;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	
//	@Autowired
//	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
//	}
	
	 @Autowired
	 public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
       authenticationManagerBuilder.userDetailsService(customUserDetailsService)
       							.passwordEncoder(passwordEncoder);
   }

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

	public void urlCheck() {
		
		
		
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers("/oauth/token").permitAll()
				.antMatchers("/api-docs/**").permitAll()
				.antMatchers("/index-assets/**").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/about-us").permitAll()
				.antMatchers("/offering-vaccineTracker").permitAll()
				.antMatchers("/offering-preventive-healthCheckup").permitAll()
				.antMatchers("/offering-mother-childTracking").permitAll()
				.antMatchers("/offering-healthInformation").permitAll()
				.antMatchers("/offering-covid19").permitAll()
				.antMatchers("/offering-advanceStemCell").permitAll()
				.antMatchers("/offering-singleIdentity-digitalSmartCard").permitAll()
				.antMatchers("/offering-hivc").permitAll()
				.antMatchers("/offering-hdms").permitAll()
				.antMatchers("/leadership").permitAll()
				.antMatchers("/what-we-offer").permitAll()
				.antMatchers("/faq").permitAll()
				.antMatchers("/news").permitAll()
				.antMatchers("/contact-us").permitAll()
				.antMatchers("/forgot-password").permitAll()
				.antMatchers("/address-wise-registration").permitAll()
				.antMatchers("/register").permitAll()
				.antMatchers("/register-add-user").permitAll()
				.antMatchers("/register-planCard").permitAll()
				.antMatchers("/user-getSpecialityList").permitAll()
				.antMatchers("/register-getStateList").permitAll()
				.antMatchers("/register-getCountryCode").permitAll()
				.antMatchers("/register-getDistList").permitAll()
				.antMatchers("/register-getCityList").permitAll()
				.antMatchers("/register-autoSearch-organization").permitAll()
				.antMatchers("/register-add-Professional").permitAll()
				.antMatchers("/register-add-details-upload-profileImage").permitAll()
				.antMatchers("/register-add-details-delete-profileImage").permitAll()
				.antMatchers("/register-add-details-upload-file").permitAll()
				.antMatchers("/user/view-patient-test-report-pdf-download").permitAll()
				.antMatchers("/order-status").permitAll()
				.antMatchers("/restaurant/kitchen-staff-order-details").permitAll()
				.antMatchers("/restaurant/kitchen-staff-order-details-modal").permitAll()
				.antMatchers("/assets/**").permitAll()
				.antMatchers("/extend/**").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/datatables/**").permitAll()
				.antMatchers("/FileUpload/**").permitAll()
				.antMatchers("/document/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/index-get-function-list/**").permitAll()
				.antMatchers("/index-get-function-list-resp").permitAll()
				.antMatchers("/index-get-activity-list").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/get-district-list").permitAll()
				.antMatchers("/get-city-list").permitAll()
				.antMatchers("/register-organisation-add").permitAll()
				.antMatchers("/register-getStateList-org").permitAll()
				.antMatchers("/register-getDistList-org").permitAll()
				.antMatchers("/register-getCityList-org").permitAll()
				.antMatchers("/download-ehealthcard").permitAll()
				.antMatchers("/get-otp").permitAll()
				.antMatchers("/save-new-password").permitAll()
				.and().formLogin().loginPage("/login").permitAll().successHandler(customAuthenticationSuccessHandler)
				.and().authorizeRequests().antMatchers("/**").authenticated()
				;
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).maximumSessions(1).expiredUrl("/login?expired");

	}

//	@Bean
//	public TokenStore tokenStore() {
//		return new InMemoryTokenStore();
//	}


//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@Bean
//	public FilterRegistrationBean corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.addAllowedOrigin("*");
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("*");
//		source.registerCorsConfiguration("/**", config);
//		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//		bean.setOrder(0);
//		return bean;
//	}

	

}
