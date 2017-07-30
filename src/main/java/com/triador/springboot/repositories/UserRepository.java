package com.triador.springboot.repositories;

import com.triador.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String name);

    User findByEmail(String email);

    List<User> findAllByBirthday(Date date);

    List<User> findAllByEmail(String email);

    List<User>findByFirstnameContainingIgnoreCase(String firstname);

    List<User>findByLastnameContainingIgnoreCase(String lastname);
}
