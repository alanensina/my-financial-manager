package alanensina.mfm.services;

import alanensina.mfm.dtos.cashControl.CashControlSaveDTO;
import alanensina.mfm.dtos.cashControl.CashControlUpdateDTO;
import alanensina.mfm.exceptions.cashControl.CashControlDeleteException;
import alanensina.mfm.exceptions.cashControl.CashControlNotFoundException;
import alanensina.mfm.exceptions.cashControl.CashControlSaveException;
import alanensina.mfm.exceptions.user.UserNotFoundException;
import alanensina.mfm.models.CashControl;
import alanensina.mfm.repositories.CashControlRepository;
import alanensina.mfm.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static alanensina.mfm.utils.Utils.CASH_CONTROL_NOT_FOUND_ID;

@Service
public class CashControlService {


    private final UserRepository userRepository;
    private final CashControlRepository cashControlRepository;

    public CashControlService(UserRepository userRepository, CashControlRepository cashControlRepository) {
        this.userRepository = userRepository;
        this.cashControlRepository = cashControlRepository;
    }

    public ResponseEntity<CashControl> save(UUID userId, CashControlSaveDTO dto) {
        var userOpt = userRepository.findById(userId);

        if(userOpt.isEmpty()){
            throw new UserNotFoundException("User not found. ID: " + userId);
        }

        var wallet = userOpt.get().getWallet();
        var cashControlEntry = new CashControl();

        BeanUtils.copyProperties(dto, cashControlEntry);
        cashControlEntry.setWallet(wallet);
        cashControlEntry.setCreatedAt(LocalDateTime.now());
        cashControlEntry.setLastUpdate(cashControlEntry.getCreatedAt());

        try{
            cashControlEntry = cashControlRepository.save(cashControlEntry);
            return ResponseEntity.status(HttpStatus.CREATED).body(cashControlEntry);
        }catch (Exception e){
            throw new CashControlSaveException("Error to save a cash control. Error: " + e.getMessage());
        }
    }

    public ResponseEntity<List<CashControl>> listAllCashControlEntriesByUserId(UUID userId) {
        var userOpt = userRepository.findById(userId);

        if(userOpt.isEmpty()){
            throw new UserNotFoundException("User not found. ID: " + userId);
        }

        var wallet = userOpt.get().getWallet();
        var cashControlList = cashControlRepository.findByWalletId(wallet.getId());

        if(cashControlList.isEmpty()){
           throw new CashControlNotFoundException("Cash control not found. Wallet ID: " + wallet.getId());
        }

        return ResponseEntity.status(HttpStatus.OK).body(cashControlList);
    }

    public ResponseEntity<CashControl> update(CashControlUpdateDTO dto) {
        var oldCashControlOpt = cashControlRepository.findById(dto.id());

        if(oldCashControlOpt.isEmpty()){
            throw new CashControlNotFoundException(CASH_CONTROL_NOT_FOUND_ID + dto.id());
        }

        var updatedCashControl = oldCashControlOpt.get();
        updatedCashControl.setLastUpdate(LocalDateTime.now());
        updatedCashControl.setType(dto.type());
        updatedCashControl.setDescription(dto.description());
        updatedCashControl.setValue(dto.value());

        try{
            updatedCashControl = cashControlRepository.save(updatedCashControl);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedCashControl);
        }catch (Exception e){
            throw new CashControlSaveException("Error to update a cash control. ID: " + updatedCashControl.getId() + ", Error: " + e.getMessage());
        }
    }

    public ResponseEntity<CashControl> findById(UUID id) {
        var cashControlOpt = cashControlRepository.findById(id);

        if(cashControlOpt.isEmpty()){
            throw new CashControlNotFoundException(CASH_CONTROL_NOT_FOUND_ID + id);
        }

        var cashControl = cashControlOpt.get();
        return ResponseEntity.status(HttpStatus.OK).body(cashControl);
    }

    public ResponseEntity<String> deleteById(UUID id) {
        var cashControlOpt = cashControlRepository.findById(id);

        if(cashControlOpt.isEmpty()){
            throw new CashControlNotFoundException(CASH_CONTROL_NOT_FOUND_ID + id);
        }

        try{
            cashControlRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Cash control ID: " + id + " deleted.");
        }catch (Exception e){
            throw new CashControlDeleteException("Error to delete a cash control. ID: " + id + ", Error: " + e.getMessage());
        }
    }
}
