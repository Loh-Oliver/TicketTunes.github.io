package csd.week5.transaction;

import java.util.List;
import csd.week5.user.User;

import javax.validation.Valid;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
public class TransactionController {
    private TransactionService TransactionService;

    public TransactionController(TransactionService bs){
        this.TransactionService = bs;
    }

    @GetMapping("/Transactions")
    public List<Transaction> getTransactions(){
        return TransactionService.listTransactions();
    }

    @GetMapping("/Transactions/{id}")
    public Transaction getTransaction(@PathVariable Long id){
        Transaction Transaction = TransactionService.getTransaction(id);
        if(Transaction == null) throw new TransactionNotFoundException(id);
        return TransactionService.getTransaction(id);

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/Transactions")
    public Transaction addTransaction(@Valid @RequestBody Transaction Transaction) {
        return TransactionService.addTransaction(Transaction);
    }

    @PutMapping("/Transactions/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @Valid @RequestBody User newUser){
        Transaction Transaction = TransactionService.updateTransaction(id, newUser);
        if(Transaction == null) throw new TransactionNotFoundException(id);
        
        return Transaction;
    }

    
    @DeleteMapping("/Transactions/{id}")
    public void deleteTransaction(@PathVariable Long id){
        try{
            TransactionService.deleteTransaction(id);
         }catch(EmptyResultDataAccessException e) {
            throw new TransactionNotFoundException(id);
         }
    }

}