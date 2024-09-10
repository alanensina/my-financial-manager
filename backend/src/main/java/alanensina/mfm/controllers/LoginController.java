package alanensina.mfm.controllers;

import alanensina.mfm.models.User;
import alanensina.mfm.services.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static alanensina.mfm.utils.Utils.FRONTEND_URL;

@CrossOrigin(origins = FRONTEND_URL)
@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService service;

    public LoginController(LoginService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<User> login(@RequestParam("email") String email, @RequestParam("password") String password){
        return service.login(email, password);
    }
}
