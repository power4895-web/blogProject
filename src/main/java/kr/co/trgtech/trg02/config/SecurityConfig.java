package kr.co.trgtech.trg02.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import kr.co.trgtech.trg02.service.UserService;


@Configuration
@EnableWebSecurity
//@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
	@Autowired
	private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
    	
    	http.csrf()
		.disable()
		;
    	
    	http.authorizeRequests()// http는 요청을 가로챔
//    	.antMatchers("/loginPage").permitAll()
////    	.anyRequest()
////    	.authenticated()
//    	.antMatchers("/signupForm").permitAll()
//    	.antMatchers("/signup").permitAll()
//    	.antMatchers("/**").authenticated()
    	.antMatchers("/loginPage").permitAll()
            .and() // 로그인 설정
            	.formLogin()
               	.loginPage("/loginPage")
            	.loginProcessingUrl("/loginPro")
            	.usernameParameter("loginId")
            	.passwordParameter("password")
            	.defaultSuccessUrl("/followingContentList") //로그인 성공 후 이동페이지
            	.permitAll()
            .and() // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/loginPage")
                .invalidateHttpSession(true)
            .and()
                // 403 예외처리 핸들링
                               .exceptionHandling().accessDeniedPage("/user/denied");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	System.out.println("auth" + auth);
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
}