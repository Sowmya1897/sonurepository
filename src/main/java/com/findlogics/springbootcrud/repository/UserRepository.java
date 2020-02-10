package com.findlogics.springbootcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.findlogics.springbootcrud.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
