package org.example.securty.Service;

import lombok.AllArgsConstructor;
import org.example.securty.Api.ApiException;
import org.example.securty.Model.User;
import org.example.securty.Rebositry.AuthRepositry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MyuserDetailsService implements UserDetailsService {

    private final AuthRepositry authRepositry;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= authRepositry.findUserByUserName(username);
        if(user==null){
            throw new ApiException("Wrong");
        }
        return user;
    }



}
