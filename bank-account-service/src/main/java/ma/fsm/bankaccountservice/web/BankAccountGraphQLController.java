package ma.fsm.bankaccountservice.web;

import ma.fsm.bankaccountservice.entities.BankAccount;
import ma.fsm.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQLController {
    /*  Alors pour que notre micro service qui est base sur GraphQl  va fonctionner on est bsoin de faire une petit configuration dans le fichier
        apllicatio.propreties la ligne suivant :
        spring.graphql.graphiql.enabled=true
     */
    @Autowired
    private BankAccountRepository bankAccountRepository  ;
    @QueryMapping
    public List<BankAccount> accounsList(){
        return bankAccountRepository.findAll() ;
    }


    @QueryMapping
    public BankAccount bankAccountsById(@Argument  String id){
        return  bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Account %s not found", id)));
    }


}
