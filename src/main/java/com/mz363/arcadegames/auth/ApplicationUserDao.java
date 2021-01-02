package com.mz363.arcadegames.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserDao extends JpaRepository<ApplicationUser,Integer> {

}
