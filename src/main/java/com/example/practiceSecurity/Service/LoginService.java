package com.example.practiceSecurity.Service;

import com.example.practiceSecurity.Config.AuthUtil;
import com.example.practiceSecurity.DTO.LoginRequest;
import com.example.practiceSecurity.DTO.LoginResponse;
import com.example.practiceSecurity.Dao.UserRepository;
import com.example.practiceSecurity.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthUtil authUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthUtil authUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authUtil = authUtil;
        this.authenticationManager = authenticationManager;
    }

    public void signUp(LoginRequest loginRequest){
        String pass = passwordEncoder.encode(loginRequest.getPassword());
        User user = new User(loginRequest.getUsername(),pass);
        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));

        User user = (User) authentication.getPrincipal();
        String token = authUtil.generateJwtToken(user);
        return new LoginResponse(Math.toIntExact(user.getId()),token);
    }
}
