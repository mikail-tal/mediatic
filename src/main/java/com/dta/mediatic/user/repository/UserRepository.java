package com.dta.mediatic.user.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dta.mediatic.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findOneByLogin(String login);
	Page<User> findAllByOrderByNameAsc(Pageable pageable);
	//User findOneByLogin(String login);

}
