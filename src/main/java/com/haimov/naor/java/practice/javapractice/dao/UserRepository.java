package com.haimov.naor.java.practice.javapractice.dao;

import com.haimov.naor.java.practice.javapractice.entity.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, String> {

}