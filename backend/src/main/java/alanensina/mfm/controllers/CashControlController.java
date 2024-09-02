package alanensina.mfm.controllers;

import alanensina.mfm.dtos.cashControl.CashControlSaveDTO;
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
    public ResponseEntity<CashControl> save(@RequestBody @Valid CashControlSaveDTO dto){
        return cashControlService.save(dto);
    }

    @GetMapping("/cash-control/list")
    public ResponseEntity<List<CashControl>> listByUser(@RequestParam UUID userId){
        return cashControlService.listAllCashControlEntriesByUserId(userId);
    }
}
