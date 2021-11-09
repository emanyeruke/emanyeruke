package com.cap10mycap10.userservice.controller;


import com.cap10mycap10.userservice.dto.*;
import com.cap10mycap10.userservice.model.Role;
import com.cap10mycap10.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Slf4j
@RestController
public class UserRestController {


    private final UserService userService;

    public UserRestController(final UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/user/registration")
    public ResponseEntity<UserResponse> registerUserAccount(@Valid @RequestBody final UserDto accountDto,
                                                            final HttpServletRequest request) {
        UserResponse userResponse = userService.registerNewUserAccount(accountDto, request);
        return ResponseEntity.ok(userResponse);
    }

    // User activation - verification
    @GetMapping("/user/resendRegistrationToken")
    public GenericResponse resendRegistrationToken(final HttpServletRequest request, @RequestParam("token") final String existingToken) {
        userService.resendRegistrationToken(request, existingToken);
        return new GenericResponse("We will send an email with a new registration token to your email account");
    }

    // Reset password
    @PostMapping("/user/resetPassword")
    public GenericResponse resetPassword(final HttpServletRequest request, @RequestParam("email") final String userEmail) {
        return userService.resetPassword(request, userEmail);
    }

    // Save password
    @PostMapping("/user/savePassword")
    public GenericResponse savePassword(final Locale locale, @Valid @RequestBody PasswordDto passwordDto) {
        log.info("Request: {}", passwordDto);
        return userService.savePassword(locale, passwordDto);
    }

    @PostMapping("/user/change-password")
    public GenericResponse changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        userService.changeUserPassword(request);
        return new GenericResponse("Password updated successfully");
    }

    @GetMapping("/users/roles/{role}")
    public ResponseEntity<List<UserResponse>> getAllUsersWithRole(@PathVariable("role") String role) {
        return ResponseEntity.ok(userService.findByRole(role));
    }

    //==============activation=============================================
    @PutMapping("/user/activate/{id}")
    public ResponseEntity<UserResponse> activate(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.activate(id));
    }

    @PutMapping("/user/deactivate/{id}")
    public ResponseEntity<UserResponse> deactivate(@Valid @PathVariable("id") Long id) {

        return ResponseEntity.ok(userService.deactivate(id));
    }

    @PostMapping("/enable-user")
    public ResponseEntity<UserResponse> enableAdminUsers(@RequestBody EnableAdminUserRequest request) {
        return ResponseEntity.ok(userService.enableUser(request));
    }

    @GetMapping("/users/{page}/{size}")
    public ResponseEntity<Page<UserResponse>> findALl(@PathVariable("page") int page,
                                                      @PathVariable("size") int size) {
        return ResponseEntity.ok(userService.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/users/admin/{page}/{size}")
    public ResponseEntity<Page<UserResponse>> findAllAdminUsers(@PathVariable("page") int page,
                                                                @PathVariable("size") int size,
                                                                @RequestParam(required = false) String search) {
        return ResponseEntity.ok(userService.findAllAdminUsers(PageRequest.of(page, size), search));
    }


    @GetMapping("/user/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(userService.findAllRoles());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/users/username/{username}")
    public ResponseEntity<UserSearchResponse> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }




    @PostMapping("/user/feignregistration")
    public UserResponse registerUserAccountFeign(@Valid @RequestBody final UserDto accountDto) {

        return userService.registerNewUserFeignAccount(accountDto);
    }

}