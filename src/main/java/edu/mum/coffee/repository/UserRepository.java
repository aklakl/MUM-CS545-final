package edu.mum.coffee.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.coffee.domain.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Serializable> {

}
