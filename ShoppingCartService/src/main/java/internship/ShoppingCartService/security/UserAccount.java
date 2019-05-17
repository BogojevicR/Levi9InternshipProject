package internship.ShoppingCartService.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import internship.ShoppingCartService.models.User;

public class UserAccount implements org.springframework.security.core.userdetails.UserDetails {


	private static final long serialVersionUID = 1L;
	
	private User user;
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder() ;
	
	public UserAccount(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(this.user.getRole().toString()));
		return authorities;
	}

	@Override
	public String getPassword() {
		return passwordEncoder.encode(this.user.getPassword());
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
