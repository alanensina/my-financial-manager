package alanensina.mfm.services;

import alanensina.mfm.dtos.investment.InvestmentRecordSaveDTO;
import alanensina.mfm.dtos.investment.InvestmentRecordUpdateDTO;
import alanensina.mfm.exceptions.investment.InvestmentDeleteException;
import alanensina.mfm.exceptions.investment.InvestmentNotFoundException;
import alanensina.mfm.exceptions.investment.SaveInvestmentException;
import alanensina.mfm.exceptions.investment.UpdateInvestmentException;
import alanensina.mfm.exceptions.user.UserNotFoundException;
import alanensina.mfm.models.Investment;
import alanensina.mfm.repositories.InvestimentRepository;
import alanensina.mfm.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class InvestmentService {

    private final InvestimentRepository investimentRepository;
    private final UserRepository userRepository;

    public InvestmentService(InvestimentRepository investimentRepository, UserRepository userRepository) {
        this.investimentRepository = investimentRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Investment> save(UUID userId, InvestmentRecordSaveDTO dto) {
        var userOpt = userRepository.findById(userId);

        if(userOpt.isEmpty()){
            throw new UserNotFoundException("User not found. ID: " + userId);
        }

        var wallet = userOpt.get().getWallet();
        var investment = new Investment();
        BeanUtils.copyProperties(dto, investment);
        investment.setWallet(wallet);
        investment.setActive(true);
        investment.setCreatedAt(LocalDateTime.now());
        investment.setLastUpdate(investment.getCreatedAt());

        try{
            investment = investimentRepository.save(investment);
            return ResponseEntity.status(HttpStatus.CREATED).body(investment);
        }catch (Exception e){
            throw new SaveInvestmentException("Error to save an investment. Error: " + e.getMessage());
        }
    }

    public ResponseEntity<Investment> findById(UUID id) {
        var investmentOpt = investimentRepository.findById(id);

        if(investmentOpt.isEmpty()){
            throw new InvestmentNotFoundException("Investment not found. ID: " + id);
        }

        return ResponseEntity.status(HttpStatus.OK).body(investmentOpt.get());
    }

    public ResponseEntity<List<Investment>> listAllInvestmentsByUserId(UUID userId) {
        var userOpt = userRepository.findById(userId);

        if(userOpt.isEmpty()){
            throw new UserNotFoundException("User not found. ID: " + userId);
        }

        var walletId = userOpt.get().getWallet().getId();
        var investments = investimentRepository.findByWalletId(walletId);

        if(investments.isEmpty()){
            throw new InvestmentNotFoundException("No investments found to user ID: " + userId);
        }

        return ResponseEntity.status(HttpStatus.OK).body(investments);
    }

    public ResponseEntity<Investment> update(InvestmentRecordUpdateDTO dto) {
        var oldInvestmentOpt = investimentRepository.findById(dto.id());

        if(oldInvestmentOpt.isEmpty()){
            throw new InvestmentNotFoundException("Investment not found. ID: " + dto.id());
        }

        var updatedInvestment = oldInvestmentOpt.get();
        updatedInvestment.setLastUpdate(LocalDateTime.now());
        updatedInvestment.setActive(dto.active());
        updatedInvestment.setCode(dto.code());
        updatedInvestment.setName(dto.name());
        updatedInvestment.setType(dto.type());
        updatedInvestment.setQuantity(dto.quantity());

        try{
            updatedInvestment = investimentRepository.save(updatedInvestment);
            return ResponseEntity.status(HttpStatus.OK).body(updatedInvestment);
        }catch (Exception e){
            throw new UpdateInvestmentException("Error to update an investment. ID: " + updatedInvestment.getId() + ", Error: " + e.getMessage());
        }
    }

    public ResponseEntity<String> deleteById(UUID id) {
        var investmentOpt = investimentRepository.findById(id);

        if(investmentOpt.isEmpty()){
            throw new InvestmentNotFoundException("Investment not found. ID: " + id);
        }

        try{
            investimentRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Investment ID: " + id + " deleted.");
        }catch (Exception e){
            throw new InvestmentDeleteException("Error to delete an investment. ID: " + id + ", Error: " + e.getMessage());
        }
    }
}
