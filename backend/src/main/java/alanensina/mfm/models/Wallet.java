package alanensina.mfm.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="WALLETS")
public class Wallet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JsonIgnore
    @OneToOne(mappedBy = "wallet")
    private User user;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Investment> investments;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CashControl> cashControl;

    @Transient private BigDecimal investmentsBalance;
    @Transient private BigDecimal cashBalance;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }

    public List<CashControl> getCashControl() {
        return cashControl;
    }

    public void setCashControl(List<CashControl> cashControl) {
        this.cashControl = cashControl;
    }

    public BigDecimal getInvestmentsBalance() {
        return investmentsBalance;
    }

    public void setInvestmentsBalance(BigDecimal investmentsBalance) {
        this.investmentsBalance = investmentsBalance;
    }

    public BigDecimal getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(BigDecimal cashBalance) {
        this.cashBalance = cashBalance;
    }
}
