package org.example.securty.Rebositry;

import org.example.securty.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepositry extends JpaRepository<User,Integer> {
    User findUserByUserName(String username);
    User findUserByUserNameAndPassword(String username,String password);

}
