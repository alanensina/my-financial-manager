package alanensina.mfm.services;

import alanensina.mfm.dtos.cashControl.CashControlSaveDTO;
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

@Service
public class CashControlService {

    private final UserRepository userRepository;
    private final CashControlRepository cashControlRepository;

    public CashControlService(UserRepository userRepository, CashControlRepository cashControlRepository) {
        this.userRepository = userRepository;
        this.cashControlRepository = cashControlRepository;
    }

    public ResponseEntity<CashControl> save(CashControlSaveDTO dto) {

        var userOpt = userRepository.findById(dto.userId());

        if(userOpt.isEmpty()){
            throw new UserNotFoundException("User not found. ID: " + dto.userId());
        }

        var user = userOpt.get();
        var wallet = user.getWallet();
        var cashControlEntry = new CashControl();

        BeanUtils.copyProperties(dto, cashControlEntry);
        cashControlEntry.setDate(LocalDateTime.now());
        cashControlEntry.setWallet(wallet);

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
}
