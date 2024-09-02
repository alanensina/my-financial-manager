package alanensina.mfm.repositories;

import alanensina.mfm.models.CashControl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CashControlRepository extends JpaRepository<CashControl, UUID> {

    List<CashControl> findByWalletId(UUID id);
}
