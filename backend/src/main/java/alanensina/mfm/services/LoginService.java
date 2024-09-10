package alanensina.mfm.services;

import alanensina.mfm.exceptions.login.InvalidPasswordException;
import alanensina.mfm.exceptions.user.UserNotFoundException;
import alanensina.mfm.models.User;
import alanensina.mfm.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<User> login(String email, String password) {

        var users = userRepository.findByEmail(email);

        if(users.isEmpty()){
            throw new UserNotFoundException("User not found.");
        }

        var user = users.getFirst();

        if(!user.getPassword().equalsIgnoreCase(password)){
            throw new InvalidPasswordException("Wrong password.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
