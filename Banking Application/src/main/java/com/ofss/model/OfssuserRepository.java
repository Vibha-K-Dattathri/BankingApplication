package com.ofss.model;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ofss.OfssUser;

public interface OfssuserRepository extends JpaRepository<OfssUser, Long> {
    Optional<OfssUser> findByUserName(String userName);
}
