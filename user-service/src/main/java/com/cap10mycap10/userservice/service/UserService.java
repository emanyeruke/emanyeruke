package com.cap10mycap10.userservice.service;


import com.cap10mycap10.userservice.dto.*;
import com.cap10mycap10.userservice.model.Role;
import com.cap10mycap10.userservice.model.User;
import com.cap10mycap10.userservice.model.VerificationToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public interface UserService {

    UserResponse registerNewUserAccount(UserDto accountDto, HttpServletRequest request);

    User getUser(String verificationToken);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken generateNewVerificationToken(String token);

    void createPasswordResetTokenForUser(User user, String token);

    User findUserByEmail(String email);

    Optional<User> getUserByPasswordResetToken(String token);

    boolean checkIfValidOldPassword(User user, String password);

    Page<UserResponse> findAll(Pageable pageable, Long agentId);

    Page<UserResponse> findAll(Pageable pageable);

    List<UserResponse> findByRole(String role);

    UserResponse activate(Long id);

    UserResponse deactivate(Long id);

    UserResponse enableUser(EnableAdminUserRequest request);

    List<Role> findAllRoles();

    void resendRegistrationToken(HttpServletRequest request, String existingToken);

    GenericResponse resetPassword(HttpServletRequest request, String userEmail);

    GenericResponse savePassword(Locale locale, PasswordDto passwordDto);

    void changeUserPassword(ChangePasswordRequest passwordDto);

    UserResponse findById(Long id);

    Page<UserResponse> findAllAdminUsers(Pageable pageable, String search);

    UserSearchResponse findByUsername(String username);

    UserResponse registerNewUserFeignAccount(UserDto accountDto);
}