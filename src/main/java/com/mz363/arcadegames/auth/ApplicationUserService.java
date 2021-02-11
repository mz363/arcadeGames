package com.mz363.arcadegames.auth;

import com.mz363.arcadegames.ArcadeGamesApplication;
import com.mz363.arcadegames.security.ApplicationUserRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mz363.arcadegames.security.ApplicationUserRoles.*;

@Service
public class ApplicationUserService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(ArcadeGamesApplication.class);

    @Autowired
    private ApplicationUserDao applicationUserDao;

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = applicationUserDao.findAll();
        return applicationUsers;
    }

    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        boolean userExists = selectApplicationUserByUsername(username).isPresent();
        if(userExists) {
            logger.info("User Exists");
            //.get() gets the application user form Optional<ApplicationUser>
            ApplicationUser selectedApplicationUser = selectApplicationUserByUsername(username).get();
            ApplicationUser applicationUser =
                    new ApplicationUser(
                            selectedApplicationUser.getId(),
                            selectedApplicationUser.getUsername(),
                            selectedApplicationUser.getPassword(),
                            selectedApplicationUser.getRole(),
                            ApplicationUserRoles.valueOf(selectedApplicationUser.getRole()).getGrantedAuthorities(),
                            selectedApplicationUser.isAccountNonExpired(),
                            selectedApplicationUser.isAccountNonLocked(),
                            selectedApplicationUser.isCredentialsNonExpired(),
                            selectedApplicationUser.isEnabled()
            );
            logger.info(applicationUser.getAuthorities().toString());

            return applicationUser;
        }
        logger.info("Username %s not found", username);

        return selectApplicationUserByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Username %s not found", username))
                );
    }

}
