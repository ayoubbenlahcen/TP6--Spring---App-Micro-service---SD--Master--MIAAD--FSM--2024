package ma.fsm.bankaccountservice.entities;

import ma.fsm.bankaccountservice.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = BankAccount.class , name = "p1")
public interface AccountProjection {
    // ici il faut declarrer les getters des entites selon les normes si on veut que les projections marche
    public String getId() ;
    public AccountType  getType() ;
}
