package alanensina.mfm.services;

import alanensina.mfm.dtos.UserRecordSaveDTO;
import alanensina.mfm.dtos.UserRecordUpdateDTO;
import alanensina.mfm.models.User;
import alanensina.mfm.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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
        BeanUtils.copyProperties(dto, user);
        user.setPassword(encryptPassword(user.getPassword()));
        user = userRepository.save(user);

        return user.getId() != null ?
                ResponseEntity.status(HttpStatus.CREATED).body(user) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    public ResponseEntity<User> findById(UUID id) {
        var opt = userRepository.findById(id);

        return opt.map(user -> ResponseEntity.status(HttpStatus.ACCEPTED).body(user)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<List<User>> listAll() {
        var users = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(users);
    }

    @Transactional
    public ResponseEntity<User> update(UserRecordUpdateDTO dto) {
        var updatedUser = new User();
        BeanUtils.copyProperties(dto, updatedUser);
        updatedUser.setPassword(encryptPassword(updatedUser.getPassword()));

        var opt = userRepository.findById(updatedUser.getId());

        return opt.map(user -> ResponseEntity.status(HttpStatus.ACCEPTED).body(userRepository.save(updatedUser))).orElseGet(() ->
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    public ResponseEntity<String> deleteById(UUID id) {
        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // TODO: Need to encrypt the password before to store in the database
    private String encryptPassword(String password){
        return password;
    }
}
