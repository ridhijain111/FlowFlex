package com.flowflex.api;

import com.flowflex.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;
    public UserController(UserService service){this.service=service;}
    @PostMapping public UserModels.UserResponse create(@RequestBody UserModels.CreateUser request){return service.create(request);}
    @GetMapping("/{id}") public UserModels.UserResponse get(@PathVariable Long id){return service.summary(id).user();}
    @PutMapping("/{id}") public UserModels.UserResponse update(@PathVariable Long id,@RequestBody UserModels.CreateUser request){return service.updateUser(id,request);}
    @PostMapping("/{id}/income") public Object addIncome(@PathVariable Long id,@RequestBody UserModels.IncomeRequest request){return service.addIncome(id,request);}
    @PutMapping("/{id}/income/{incomeId}") public void updateIncome(@PathVariable Long id,@PathVariable Long incomeId,@RequestBody UserModels.IncomeRequest request){service.updateIncome(id,incomeId,request);}
    @DeleteMapping("/{id}/income/{incomeId}") public void deleteIncome(@PathVariable Long id,@PathVariable Long incomeId){service.deleteIncome(id,incomeId);}
    @GetMapping("/{id}/income") public List<?> income(@PathVariable Long id){return service.income(id);}
    @PutMapping("/income/{incomeId}") public void updateIncomeAlias(@PathVariable Long incomeId,@RequestParam Long userId,@RequestBody UserModels.IncomeRequest request){service.updateIncome(userId,incomeId,request);}
    @DeleteMapping("/income/{incomeId}") public void deleteIncomeAlias(@PathVariable Long incomeId,@RequestParam Long userId){service.deleteIncome(userId,incomeId);}
    @PostMapping("/{id}/profile") public UserModels.UserResponse profile(@PathVariable Long id,@RequestBody UserModels.ProfileRequest request){return service.saveProfile(id,request);}
    @PutMapping("/{id}/profile") public UserModels.UserResponse updateProfile(@PathVariable Long id,@RequestBody UserModels.ProfileRequest request){return service.saveProfile(id,request);}
    @GetMapping("/{id}/profile") public Object getProfile(@PathVariable Long id){return service.summary(id).user().profile();}
    @PostMapping("/{id}/loan") public UserModels.UserResponse loan(@PathVariable Long id,@RequestBody UserModels.LoanRequest request){return service.saveLoan(id,request);}
    @PutMapping("/{id}/loan") public UserModels.UserResponse updateLoan(@PathVariable Long id,@RequestBody UserModels.LoanRequest request){return service.saveLoan(id,request);}
    @GetMapping("/{id}/loan") public Object getLoan(@PathVariable Long id){return service.summary(id).loan();}
    @GetMapping("/{id}/recommendation") public Object recommendation(@PathVariable Long id){return service.summary(id).recommendation();}
    @PostMapping("/{id}/recommendation/accept") public Object accept(@PathVariable Long id){return service.accept(id);}
    @PostMapping("/{id}/recommendation/customize") public Object customize(@PathVariable Long id,@RequestBody UserModels.CustomizeRequest request){return service.customize(id,request);}
    @PostMapping("/{id}/recommendation/skip") public Object skip(@PathVariable Long id){return service.skip(id);}
    @GetMapping("/{id}/financial-summary") public UserModels.PersonalizedSummary summary(@PathVariable Long id){return service.summary(id);}
    @ExceptionHandler(NoSuchElementException.class) @ResponseStatus(HttpStatus.NOT_FOUND) public Map<String,String> notFound(Exception e){return Map.of("error",e.getMessage());}
    @ExceptionHandler(IllegalArgumentException.class) @ResponseStatus(HttpStatus.BAD_REQUEST) public Map<String,String> badRequest(Exception e){return Map.of("error",e.getMessage());}
}
