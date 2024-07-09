package ma.fsm.bankaccountservice.web;

import ma.fsm.bankaccountservice.dto.BankAccountRequestDTO;
import ma.fsm.bankaccountservice.dto.BankAccountResponceDTO;
import ma.fsm.bankaccountservice.entities.BankAccount;
import ma.fsm.bankaccountservice.mappers.AccountMapper;
import ma.fsm.bankaccountservice.repositories.BankAccountRepository;
import ma.fsm.bankaccountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api") // pour faire acceder au restApi il faut
public class AccountRestController {

    @Autowired
    private AccountService accountService ;
    @Autowired
    private AccountMapper accountMapper ;

    // celui la est un connecteur , et generalement les connecteurs sont des controlleurs
    private final BankAccountRepository bankAccountRepository ;

    public AccountRestController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    // ce Rest API pour faire recouperer tous les comptes
    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts (){
        return  bankAccountRepository.findAll() ;
    }

    // ce Rest API pour recouperer un compte en utilisant son id
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount (@PathVariable String id){
        return  bankAccountRepository.findById(id)
                                     .orElseThrow(()->new RuntimeException(String.format("Account %s not found ", id))) ;
    }

    @PostMapping("/save")
    public BankAccountResponceDTO save (@RequestBody BankAccountRequestDTO requestDTO){
        // ici il faut indique que les données de  bankAccount  on va les recouperer dans le corps de la requet au format json
        //if(bankAccount.getId() ==null) bankAccount.setId(UUID.randomUUID().toString());
        // normalement ça sont des truc de la couche metier  c'est a dire verifer si le id ==null
        //bankAccount.setCreatedAt(new Date());
        return accountService.addAccount(requestDTO) ;
    }

    @PutMapping("/updateBankAccount/{id}")
    public BankAccount updateBankAccount (@PathVariable String id , @RequestBody BankAccount bankAccount){
        /*  Ici il faut indique que les données de  bankAccount  on va les recouperer dans le corps de la requet au format json

            Alors pour faire mettre a joue les données d'un compte il faut aller chercher le compte dans la base de donne
            en utlisant  le id specifier dans le path puis faire en suite les mise a jours .
         */
        BankAccount account = bankAccountRepository.findById(id).orElseThrow();

        /*
        On essayer de faire  traiter cas ou les notre objet recouper a partie de la requet contient quelque attribue
        alors il faut traiter ce cas pour ne pas perdre les donne de compte dans le cas ou ces dernnier sont null
         */

        /*
        Et comme ca notre Rest Api il va faire  le put c'est  a dire faire modifier toute les attribues
        et aussi le pach  c'est a dire faire modifier seulement quelque attrubue qui ont onjyer dans la requet
         */
        if(bankAccount.getBalance() != null) account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCreatedAt() != null) account.setCreatedAt(new Date());
        if(bankAccount.getType() != null) account.setType(bankAccount.getType());
        if(bankAccount.getCurrency() != null) account.setCurrency(bankAccount.getCurrency());

        return bankAccountRepository.save(account) ;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@RequestParam String id){
        bankAccountRepository.deleteById(id);
    }

}
