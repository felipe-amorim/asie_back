package com.mkyong.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
public class ProductController {

    private void throwUserAccessExceptionAdmin(String session){
        //String userName = SessionRepository.getUserName(session);
        //if (userName.length() > 0) {
        //    String accessLevel = SessionRepository.getUserAccess(userName);
        //    if (accessLevel.length() > 0) {
        //        if (!accessLevel.equals(SessionRepository.AccessLevel.admin)) {
        //            throw new ResponseStatusException(FORBIDDEN);
        //        }
        //    } else {
        //        throw new ResponseStatusException(INTERNAL_SERVER_ERROR);
        //    }
        //} else {
        //    throw new ResponseStatusException(UNAUTHORIZED);
        //}
    }

    private void throwUserAccessException(String session){
        //String userName = SessionRepository.getUserName(session);
        //if (userName.length()>0){
        //    String accessLevel = SessionRepository.getUserAccess(userName);
        //    if(accessLevel.length()>0){
        //        if(!accessLevel.equals(SessionRepository.AccessLevel.admin) && !accessLevel.equals(SessionRepository.AccessLevel.user)){
        //            throw new ResponseStatusException(FORBIDDEN);
        //        }
        //    }else{
        //        throw new ResponseStatusException(INTERNAL_SERVER_ERROR);
        //    }
        //}else{
        //    throw new ResponseStatusException(UNAUTHORIZED);
        //}
    }

    private boolean isDuplicatedName(Product newProduct){
        List<Product> products = repository.findAll();
        boolean duplicated = false;
        for (Product existingProduct:products) {
            if(existingProduct.getName().equalsIgnoreCase(newProduct.getName())){
                duplicated = true;
                break;
            }
        }
        return duplicated;
    }

    @Autowired
    private ProductRepository repository;

    @GetMapping("/products")
    List<Product> findAll(@RequestHeader("Authorization") String session) {
        throwUserAccessException(session);
        return repository.findAll();
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    Product newProduct(@RequestHeader("Authorization") String session, @Valid @RequestBody Product newProduct) {
        throwUserAccessExceptionAdmin(session);
        if(isDuplicatedName(newProduct)){
            throw new ResponseStatusException(CONFLICT, "Product with name '"+newProduct.getName()+"' already exists");
        }else {
            return repository.save(newProduct);
        }
    }

    @GetMapping("/products/{id}")
    Product findOne(@RequestHeader("Authorization") String session, @PathVariable @Min(1) Long id) {
        throwUserAccessException(session);
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Product with id '"+id+"' not found"));
    }

    @PutMapping("/products/{id}")
    Product saveOrUpdate(@RequestHeader("Authorization") String session, @RequestBody Product newProduct, @PathVariable Long id) {
        throwUserAccessExceptionAdmin(session);
        return repository.findById(id)
                .map(x -> {
                    x.setName(newProduct.getName());
                    x.setDescription(newProduct.getDescription());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
    }

    @DeleteMapping("/products/{id}")
    void deleteBook(@RequestHeader("Authorization") String session, @PathVariable Long id) {
        throwUserAccessExceptionAdmin(session);
        repository.deleteById(id);
    }
}
