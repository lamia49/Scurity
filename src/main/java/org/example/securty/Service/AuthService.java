package org.example.securty.Service;

import lombok.RequiredArgsConstructor;
import org.example.securty.Api.ApiException;
import org.example.securty.Model.User;
import org.example.securty.Rebositry.AuthRepositry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepositry authRepositry;
    public void register(User user){
        user.setRole("CUSTOMER");
        String hashPassword=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPassword);
        authRepositry.save(user);
    }

    public void login(String username ,String password){
       User user = authRepositry.findUserByUserNameAndPassword(username,password );
    }

    public void logout(){
    }


    public void update(String username,User user){
        User user1= authRepositry.findUserByUserName(username);

        if(user1==null){
                 throw  new ApiException("User name Not Found!");
        }
            user1.setUserName(user.getUsername());
            user1.setPassword(user.getPassword());
            authRepositry.save(user1);

    }

    public void delete(String username,String username2){
          User user= authRepositry.findUserByUserName(username);
          User user2= authRepositry.findUserByUserName(username);
          if(user2==null){
               throw  new ApiException("User name Not Found!");
          }
        if(user.getRole().equals("ADMIN")){
            authRepositry.delete(user2);
        }
        throw  new ApiException("Allows only Admin!");
    }

    public List<User>getUsers(String username){
              User user= authRepositry.findUserByUserName(username);
        if(user.getRole().equals("ADMIN")){
            authRepositry.delete(user);
        }
        return  authRepositry.findAll();
    }




}
