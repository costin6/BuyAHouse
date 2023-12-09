package com.fontys.s3.buyahouse.controller;

import com.fontys.s3.buyahouse.business.*;
import com.fontys.s3.buyahouse.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class AuthController {
    private final LoginUseCase loginUseCase;
    private final SignUpUseCase signUpUseCase;
    private final GetUserDetailsUseCase getUserDetailsUseCase;
    private final UpdateUserDetailsUseCase updateUserDetailsUseCase;
    private final DeleteUserAccountUseCase deleteUserAccountUseCase;

    @PostMapping("/tokens")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = loginUseCase.login(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
    }

    @PostMapping()
    public ResponseEntity<SignUpResponse> signup(@RequestBody @Valid SignUpRequest signUpRequest) {
        SignUpResponse signUpResponse = signUpUseCase.registerUser(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpResponse);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetUserDetailsResponse> getLoggedUserDetails(@PathVariable("userId")Long userId){
        GetUserDetailsResponse response = getUserDetailsUseCase.getLoggedUserDetails(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserDetails(@PathVariable("userId") Long id,
                                                  @RequestBody @Valid UpdateUserDetailsRequest request){
        request.setUserId(id);
        updateUserDetailsUseCase.updateUserDetails(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUserAccount(@PathVariable("userId") Long userId){
        deleteUserAccountUseCase.deleteUserAccount(userId);
        return ResponseEntity.noContent().build();
    }
}
