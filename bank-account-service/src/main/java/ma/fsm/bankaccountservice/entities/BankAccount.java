package ma.fsm.bankaccountservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.fsm.bankaccountservice.enums.AccountType;

import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder  // c'est un patterne poiur creer les objets
public class BankAccount {
    @Id
    private String id  ;
    private Date createdAt  ;
    private Double balance ; // cette indique que la valuer par defaut de  balance sera null
    private String currency ;
    @Enumerated(EnumType.STRING)
    private AccountType type  ;

}
