package com.mz363.arcadegames.auth;

import com.mz363.arcadegames.ArcadeGamesApplication;
import com.mz363.arcadegames.exception.ResourceNotFoundException;

import com.mz363.arcadegames.security.ApplicationUserRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.mz363.arcadegames.security.ApplicationUserRoles.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class ApplicationUserController {

    private static final Logger logger = LoggerFactory.getLogger(ArcadeGamesApplication.class);
    private final PasswordEncoder passwordEncoder;
    private ApplicationUserDao applicationUserDao;

    @Autowired
    public ApplicationUserController(PasswordEncoder passwordEncoder, ApplicationUserDao applicationUserDao) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserDao = applicationUserDao;
    }

    @GetMapping("/applicationUsers")
    public List<ApplicationUser> getAllUsers() {
        logger.info("Get all the users...");
        return applicationUserDao.findAll();
    }

    @GetMapping("/applicationUsers/{id}")
    public ResponseEntity<ApplicationUser> getApplicationUserById(@PathVariable(value = "id") Integer applicationUserId) throws ResourceNotFoundException {
        logger.info("Get ApplicationUser by id...");
        ApplicationUser applicationUser = applicationUserDao.findById(applicationUserId).
                orElseThrow(() -> new ResourceNotFoundException("ApplicationUser not found for this id:: " + applicationUserId));
        return ResponseEntity.ok().body(applicationUser);

    }

    @PostMapping("/newUser")
    public ApplicationUser createApplicationUser(@Valid @RequestBody ApplicationUser applicationUser) {
        ApplicationUser saveApplicationUser =
                new ApplicationUser(
                        applicationUser.getId(),
                        applicationUser.getUsername(),
                        passwordEncoder.encode(applicationUser.getPassword()),
                        applicationUser.getRole(),
                        ApplicationUserRoles.valueOf(applicationUser.getRole()).getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                );
        logger.info("Insert applicationUser..." + applicationUser.getPassword());
        return applicationUserDao.save(saveApplicationUser);
    }

//    @PutMapping("/applicationUser/{id}")
//    public ResponseEntity<ApplicationUser> applicationUserById(@PathVariable(value = "id") long playerId, @RequestBody Player updatedPlayer) throws ResourceNotFoundException {
//        logger.info("Update player...");
//        Player player = repository.findById(playerId).
//                orElseThrow(() -> new ResourceNotFoundException("Player not found for this id:: " + playerId));
//        player.setName(updatedPlayer.getName());
//        player.setHighScore(updatedPlayer.getHighScore());
//        player.setAttempts(updatedPlayer.getAttempts());
//        repository.save(player);
//        return ResponseEntity.ok().body(player);
//
//    }

//    @DeleteMapping("/players/{id}")
//    public void deletePlayer(@PathVariable(value = "id") long playerId) throws ResourceNotFoundException {
//        logger.info("Delete player...");
//        Player player = repository.findById(playerId).
//                orElseThrow(() -> new ResourceNotFoundException("Player not found for this id:: " + playerId));
//        repository.delete(player);
//    }
}