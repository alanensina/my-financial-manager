package alanensina.mfm.controllers;

import alanensina.mfm.dtos.cashControl.CashControlSaveDTO;
import alanensina.mfm.dtos.cashControl.CashControlUpdateDTO;
import alanensina.mfm.models.CashControl;
import alanensina.mfm.services.CashControlService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static alanensina.mfm.utils.Utils.FRONTEND_URL;

@CrossOrigin(origins = FRONTEND_URL)
@RestController
@RequestMapping("/api/cash-control")
public class CashControlController {

    private final CashControlService cashControlService;

    public CashControlController(CashControlService cashControlService) {
        this.cashControlService = cashControlService;
    }

    @PostMapping
    public ResponseEntity<CashControl> save(@RequestParam UUID userId, @RequestBody @Valid CashControlSaveDTO dto){
        return cashControlService.save(userId, dto);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CashControl>> listByUser(@RequestParam UUID userId){
        return cashControlService.listAllCashControlEntriesByUserId(userId);
    }

    @PutMapping
    public ResponseEntity<CashControl> update(@RequestBody @Valid CashControlUpdateDTO dto){
        return cashControlService.update(dto);
    }

    @GetMapping
    public ResponseEntity<CashControl> findById(@RequestParam("id") UUID id){
        return cashControlService.findById(id);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteById(@RequestParam("id") UUID id){
        return cashControlService.deleteById(id);
    }
}
