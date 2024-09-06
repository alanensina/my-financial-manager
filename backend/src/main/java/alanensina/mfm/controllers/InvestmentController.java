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

import static alanensina.mfm.utils.Utils.FRONTEND_URL;

@CrossOrigin(origins = FRONTEND_URL)
@RestController
@RequestMapping("/api/investment")
public class InvestmentController {

    private final InvestmentService investmentService;

    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @PostMapping
    public ResponseEntity<Investment> save(@RequestParam UUID userId, @RequestBody @Valid InvestmentRecordSaveDTO dto){
        return investmentService.save(userId, dto);
    }

    @GetMapping
    public ResponseEntity<Investment> findById(@RequestParam("id") UUID id){
        return investmentService.findById(id);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Investment>> listByUser(@RequestParam UUID userId){
        return investmentService.listAllInvestmentsByUserId(userId);
    }

    @PutMapping
    public ResponseEntity<Investment> update(@RequestBody @Valid InvestmentRecordUpdateDTO dto){
        return investmentService.update(dto);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteById(@RequestParam("id") UUID id){
        return investmentService.deleteById(id);
    }
}
