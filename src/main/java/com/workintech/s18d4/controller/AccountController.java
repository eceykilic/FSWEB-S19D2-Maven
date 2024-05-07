package com.workintech.s18d4.controller;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.exceptions.AccountResponse;
import com.workintech.s18d4.exceptions.CustomerResponse;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;
    private CustomerService customerService;

    @GetMapping
    public List<Account> findAll(){
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account findAll(@PathVariable long id){
        return accountService.find(id);
    }

    @PostMapping("/{customerId}")
        public AccountResponse save(@PathVariable("customerId") long customerId, @RequestBody Account account){
        Customer customer = customerService.find(customerId);
        //customera account eklemek için ama mevcut accountlarını da
        //bozmaması gerekir
        if(customer != null){
            //iki yönlü ekledik
            customer.getAccounts().add(account);
            account.setCustomer(customer);
            //bir tane save göndermek yeterli çünkü join column bu tarafta
            //mapping seviyesinde söylemek yeterli
            accountService.save(account);
        } else {
            throw new RuntimeException("no customer found");
        }
        return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount(),
                new CustomerResponse(customer.getId(), customer.getEmail(), customer.getSalary()));
        //bu id nereden geldi?
        //accountService.save(account);  buradan erişiyoruz idye

    }

    @PostMapping("/{customerId}")
    public AccountResponse update(@RequestBody Account account, @PathVariable long customerId){
        Customer customer = customerService.find(customerId);
        Account toBeUpdated = null;
        for (Account account1: customer.getAccounts()){
            if(account.getId() == account1.getId()){
                toBeUpdated = account1;
                //account listesi içerisinden update oalcak account çıkarıldı
            }
        }
        if(toBeUpdated == null){
            throw new RuntimeException("Account not found");
            //olmama akışını durdurduk
        }

        //bulunursa:
        //indexine ihtiyacımız vardı bunu bulduk
        //index buldurmayı yaptık çünkü ArrayList olduğu için
        int indexOfFound = customer.getAccounts().indexOf(toBeUpdated);
        customer.getAccounts().set(indexOfFound, account);
        account.setCustomer(customer);
        accountService.save(account);
        return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount(),
                new CustomerResponse(customer.getId(), customer.getEmail(), customer.getSalary()));
    }


    @DeleteMapping("/{id}")
    public AccountResponse remove(@PathVariable long id){
        Account account = accountService.find(id);

        if(account == null){
            throw new RuntimeException("Account not found");
        }
        accountService.delete(id);
        return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount(),
                new CustomerResponse(account.getCustomer().getId(), account.getCustomer().getEmail(), account.getCustomer().getSalary()));
    }

}