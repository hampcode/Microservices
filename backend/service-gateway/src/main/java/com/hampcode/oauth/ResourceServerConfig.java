package com.hampcode.oauth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		 .antMatchers("/api/service-oauth/oauth/token")
		 .permitAll()
		 
		.antMatchers(HttpMethod.GET,
				"/api/service-products/products",
				"/api/service-items/items",
				"/api/service-users/users")
		.permitAll()
		
		/*.antMatchers(HttpMethod.GET,
				"/api/service-products/products/{id}",
				//"/api/service-items/items/product/{id}/quantity/{quantity}",
				"/api/service-items/items/product/{id}",
				"/api/service-users/users/{id}")
		.hasAnyRole("ADMIN","USER")*/
		
		.antMatchers(HttpMethod.GET,
				"/api/service-products/products/{id}",
				"/api/service-items/items/product/{id}",
				"/api/service-items/items")
		.hasAnyRole("USER")
		.anyRequest().authenticated()
		
		.antMatchers(HttpMethod.POST,"/api/service-items/**")
		.hasRole("USER")
		.anyRequest().authenticated()
		
		.antMatchers(HttpMethod.GET,"/api/service-items/**")
		.hasRole("USER")
		.anyRequest().authenticated()
		
		
		.antMatchers("/api/service-products/**",
				"/api/service-users/**")
		.hasRole("ADMIN")
		.anyRequest().authenticated()
		
		.and().cors().configurationSource(corsConfigurationSource());
		
		/*.antMatchers(HttpMethod.POST,"/api/service-products/products","/api/service-users/users").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT,"/api/service-products/products/{id}","/api/service-users/users/{id}").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/api/service-products/products/{id}","/api/service-users/users/{id}").hasRole("ADMIN");*/
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig=new CorsConfiguration();
		corsConfig.setAllowedOrigins(Arrays.asList("*"));
		corsConfig.setAllowedMethods(Arrays.asList("POST","GET","PUT","DELETE","OPTIONS"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
		
		UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		
		return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean= new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter =new JwtAccessTokenConverter();
		tokenConverter.setSigningKey("algun_codigo_secreto_aeiou");
		return tokenConverter;
	}
}
