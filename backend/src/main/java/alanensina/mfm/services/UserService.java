package alanensina.mfm.services;

import alanensina.mfm.dtos.user.UserRecordSaveDTO;
import alanensina.mfm.dtos.user.UserRecordUpdateDTO;
import alanensina.mfm.exceptions.user.*;
import alanensina.mfm.models.User;
import alanensina.mfm.models.Wallet;
import alanensina.mfm.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseEntity<User> save(UserRecordSaveDTO dto) {
        var user = new User();
        var wallet = new Wallet();
        BeanUtils.copyProperties(dto, user);
        user.setPassword(encryptPassword(user.getPassword()));
        user.setWallet(wallet);

        if(isAnInvalidEmail(user.getEmail())){
            throw new InvalidEmailException("Email already exists.");
        }

        try{
            user = userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }catch (Exception e){
            throw new SaveUserException("Error to save an user. Error: " + e.getMessage());
        }
    }

    private boolean isAnInvalidEmail(String email) {
        var users = userRepository.findAllByEmail(email);
        return !users.isEmpty();
    }

    public ResponseEntity<User> findById(UUID id) {
        var opt = userRepository.findById(id);
        return opt.map(
                user -> ResponseEntity.status(HttpStatus.OK).body(user))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Transactional
    public ResponseEntity<User> update(UserRecordUpdateDTO dto) {
        var opt = userRepository.findById(dto.id());

        if(opt.isEmpty()){
            throw new UserNotFoundException("User ID not found: " + dto.id());
        }

        var updatedUser = new User();
        BeanUtils.copyProperties(dto, updatedUser);
        updatedUser.setPassword(encryptPassword(updatedUser.getPassword()));

        try{
            userRepository.save(updatedUser);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        }catch (Exception e){
            throw new UpdateUserException("Error to update the user id: " + dto.id() + ", Error message: " + e.getMessage());
        }
    }

    public ResponseEntity<String> deleteById(UUID id) {
        try {
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            throw new DeleteUserException("Error to delete the user id: " + id + ", Error message: " + e.getMessage());
        }
    }

    // TODO: Need to encrypt the password before to store in the database
    private String encryptPassword(String password){
        return password;
    }
}
