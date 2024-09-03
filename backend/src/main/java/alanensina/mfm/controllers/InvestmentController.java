package alanensina.mfm.controllers;

import alanensina.mfm.dtos.investment.InvestmentRecordSaveDTO;
import alanensina.mfm.dtos.investment.InvestmentRecordUpdateDTO;
import alanensina.mfm.models.Investment;
import alanensina.mfm.services.InvestmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class InvestmentController {

    private final InvestmentService investmentService;

    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @PostMapping("/investment")
    public ResponseEntity<Investment> save(@RequestParam UUID userId, @RequestBody @Valid InvestmentRecordSaveDTO dto){
        return investmentService.save(userId, dto);
    }

    @GetMapping("/investment")
    public ResponseEntity<Investment> findById(@RequestParam("id") UUID id){
        return investmentService.findById(id);
    }

    @GetMapping("/investment/list")
    public ResponseEntity<List<Investment>> listByUser(@RequestParam UUID userId){
        return investmentService.listAllInvestmentsByUserId(userId);
    }

    @PutMapping("/investment")
    public ResponseEntity<Investment> update(@RequestBody @Valid InvestmentRecordUpdateDTO dto){
        return investmentService.update(dto);
    }

    @DeleteMapping("/investment")
    public ResponseEntity<String> deleteById(@RequestParam("id") UUID id){
        return investmentService.deleteById(id);
    }
}
