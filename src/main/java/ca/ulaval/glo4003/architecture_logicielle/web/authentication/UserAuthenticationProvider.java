package ca.ulaval.glo4003.architecture_logicielle.web.authentication;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import ca.ulaval.glo4003.architecture_logicielle.dao.UserRepository;
import ca.ulaval.glo4003.architecture_logicielle.model.UserEntry;

public class UserAuthenticationProvider implements AuthenticationProvider {

	private UserRepository repository;
	
	@Inject
	public UserAuthenticationProvider(UserRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		try {
			UserEntry user = repository.getUserByEmail(name);
			return validateUserPassword(user, password);
		} catch (Exception e) {
			return null;
		}
	}

	private Authentication validateUserPassword(UserEntry user, String password) {
		if (user.isPasswordValid(password)) {
			List<GrantedAuthority> roles = new ArrayList<>();
			roles.add((new SimpleGrantedAuthority(user.getRole())));
			return new UsernamePasswordAuthenticationToken(user, password, roles);
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authenticationClass) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationClass));
	}
	
}