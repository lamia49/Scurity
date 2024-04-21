package org.example.securty.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.securty.Model.User;
import org.example.securty.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/v1/autho")
@RestController
public class AuthController {
    private final AuthService authService;



    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user){
        authService.register(user);
        return ResponseEntity.status(200).body("registered successfully");
    }
   @PostMapping("/login/{username}/{password}")
    public ResponseEntity login(@PathVariable String username ,@PathVariable String password){
        authService.login(username ,password);
        return ResponseEntity.status(200).body("login successfully");
    }
@GetMapping("/get/{username}")
    public ResponseEntity get(@PathVariable String username){
        return ResponseEntity.status(200).body(authService.getUsers(username));
    }

     @PostMapping("/logout/{username}/{password}")
    public ResponseEntity logout(){
        authService.logout();
        return ResponseEntity.status(200).body("logout successfully");
    }

    @PutMapping("/update/{username}")
    public ResponseEntity updateUser(@PathVariable String username,@RequestBody @Valid  User user){
        authService.update(username,user);
         return ResponseEntity.status(200).body("Updated successfully");
    }

      @DeleteMapping("/delete/{username}/{username2}")
    public ResponseEntity deleteUser(@PathVariable String username ,@PathVariable String username2){
        authService.delete(username,username2);
         return ResponseEntity.status(200).body("deleted successfully");
    }
}
