package ua.kiev.prog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.kiev.prog.Actions;
import ua.kiev.prog.Group;
import ua.kiev.prog.User;

import java.lang.Override;import java.lang.String;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private Actions actions;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = actions.getUserByUserName(username);

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        CustomUserDetailsUser customUserDetailsUser = new CustomUserDetailsUser(
        		user.getUsername(),
                user.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAutorities(user.getGroup(), user),
                user.getSalt(),
                user.getPhone()
        );

        return customUserDetailsUser;
    }

    public Collection<? extends GrantedAuthority> getAutorities(Group group, User user) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(group, user));
        return authList;
    }

    public List<String> getRoles(Group group, User user) {
        List<String> roles = new ArrayList<String>();
        JDBCAccess access = new JDBCAccess();
        try {
        	if(!user.getUsername().equals("superadmin")){
			user.setAuthorized(access.getAuthorizedByName(user.getUsername()));
        	}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if(user.isAuthorized() == true && group != null && group.getName().equals("Admins")) {
            roles.add("ROLE_ADMIN");
            roles.add("ROLE_USER");
        }
        if(user.isAuthorized() == true && group != null && group.getName().equals("Superadmins")) {
        	roles.add("ROLE_ADMIN");
            roles.add("ROLE_USER");
            roles.add("ROLE_SUPERADMIN");
        } 
        if(user.isAuthorized() == true && group == null) {
            roles.add("ROLE_USER");
        }
        else {
            roles.add("ROLE_NUBE");
        }

        return roles;
    }

    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }
    
}
