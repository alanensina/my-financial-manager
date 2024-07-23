package alanensina.mfm.controllers;

import alanensina.mfm.dtos.UserRecordSaveDTO;
import alanensina.mfm.dtos.UserRecordUpdateDTO;
import alanensina.mfm.models.User;
import alanensina.mfm.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/user")
    public ResponseEntity<User> save(@RequestBody @Valid UserRecordSaveDTO dto){
        return service.save(dto);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") UUID id){
        return service.findById(id);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> list(){
        return service.listAll();
    }

    @PutMapping("/user")
    public ResponseEntity<User> update(@RequestBody @Valid UserRecordUpdateDTO dto){
        return service.update(dto);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") UUID id){
        return service.deleteById(id);
    }
}
