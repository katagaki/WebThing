package com.kaminari.WebThing.Users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kaminari.WebThing.Users.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}
