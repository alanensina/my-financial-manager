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

@RestController
public class CashControlController {

    private final CashControlService cashControlService;

    public CashControlController(CashControlService cashControlService) {
        this.cashControlService = cashControlService;
    }

    @PostMapping("/cash-control/save")
    public ResponseEntity<CashControl> save(@RequestParam UUID userId, @RequestBody @Valid CashControlSaveDTO dto){
        return cashControlService.save(userId, dto);
    }

    @GetMapping("/cash-control/list")
    public ResponseEntity<List<CashControl>> listByUser(@RequestParam UUID userId){
        return cashControlService.listAllCashControlEntriesByUserId(userId);
    }

    @PutMapping("/cash-control/update")
    public ResponseEntity<CashControl> update(@RequestBody @Valid CashControlUpdateDTO dto){
        return cashControlService.update(dto);
    }

    @GetMapping("/cash-control")
    public ResponseEntity<CashControl> findById(@RequestParam("id") UUID id){
        return cashControlService.findById(id);
    }

    @DeleteMapping("/cash-control")
    public ResponseEntity<String> deleteById(@RequestParam("id") UUID id){
        return cashControlService.deleteById(id);
    }
}
