package alanensina.mfm.repositories;

import alanensina.mfm.models.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InvestimentRepository extends JpaRepository<Investment, UUID> {

    List<Investment> findByWalletId(UUID id);
}
