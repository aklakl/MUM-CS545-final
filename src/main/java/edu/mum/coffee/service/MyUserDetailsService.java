package edu.mum.coffee.service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.coffee.domain.Authorities;
import edu.mum.coffee.repository.UserRepository;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		edu.mum.coffee.domain.Users users = userRepository.findOne(username);
		
		
		List<GrantedAuthority> authorities =
                	buildUserAuthority(users.getAuthorities());
		
		return buildUserForAuthentication(users, authorities);
	}
	
	private User buildUserForAuthentication(edu.mum.coffee.domain.Users user,
			List<GrantedAuthority> authorities) {
			return new User(user.getUserName(), user.getPassword(),
				user.isEnabled(), true, true, true, authorities);
		}

		private List<GrantedAuthority> buildUserAuthority(Set<Authorities> userRoles) {

			Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

			// Build user's authorities
			for (Authorities userRole : userRoles) {
				setAuths.add(new SimpleGrantedAuthority(userRole.getAuthority()));
			}

			List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

			return Result;
		}

}
