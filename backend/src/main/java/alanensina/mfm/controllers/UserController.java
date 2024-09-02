package alanensina.mfm.controllers;

import alanensina.mfm.dtos.user.UserRecordSaveDTO;
import alanensina.mfm.dtos.user.UserRecordUpdateDTO;
import alanensina.mfm.models.User;
import alanensina.mfm.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user")
    public ResponseEntity<User> findById(@RequestParam("id") UUID id){
        return service.findById(id);
    }

    @PutMapping("/user")
    public ResponseEntity<User> update(@RequestBody @Valid UserRecordUpdateDTO dto){
        return service.update(dto);
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deleteById(@RequestParam("id") UUID id){
        return service.deleteById(id);
    }
}
