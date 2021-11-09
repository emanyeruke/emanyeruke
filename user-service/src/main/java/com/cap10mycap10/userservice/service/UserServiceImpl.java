package com.cap10mycap10.userservice.service;

import com.cap10mycap10.userservice.dto.*;
import com.cap10mycap10.userservice.mapper.UserToUserResponse;
import com.cap10mycap10.userservice.model.PasswordResetToken;
import com.cap10mycap10.userservice.model.Role;
import com.cap10mycap10.userservice.model.User;
import com.cap10mycap10.userservice.model.VerificationToken;
import com.cap10mycap10.userservice.repository.PasswordResetTokenRepository;
import com.cap10mycap10.userservice.repository.RoleRepository;
import com.cap10mycap10.userservice.repository.UserRepository;
import com.cap10mycap10.userservice.repository.VerificationTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.invenico.springcommonsmodule.exception.InvalidRequestException;
import zw.co.invenico.springcommonsmodule.exception.RecordNotFoundException;
import zw.co.invenico.springcommonsmodule.jpa.CustomSpecificationTemplateImplBuilder;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserToUserResponse toUserResponse;

    private final UserRepository userRepository;

    private final VerificationTokenRepository tokenRepository;

    private final PasswordResetTokenRepository passwordTokenRepository;

    private final EmailServiceImpl emailService;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final ISecurityUserService securityUserService;

    @Value("${test-portal.url}")
    private String url;

    public UserServiceImpl(final UserToUserResponse toUserResponse,
                           final UserRepository userRepository,
                           final VerificationTokenRepository tokenRepository,
                           final PasswordResetTokenRepository passwordTokenRepository,
                           EmailServiceImpl emailService, final PasswordEncoder passwordEncoder,
                           final RoleRepository roleRepository,
                           ISecurityUserService securityUserService) {
        this.toUserResponse = toUserResponse;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordTokenRepository = passwordTokenRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.securityUserService = securityUserService;
    }

    @Override
    @Transactional
    public UserResponse registerNewUserAccount(UserDto accountDto, HttpServletRequest request) {
        if (emailExists(accountDto.getEmail())) {
            throw new InvalidRequestException("There is an account with that email address: " + accountDto.getEmail());
        }
        if (usernameExists(accountDto.getUsername())) {
            throw new InvalidRequestException("There is an account with that email address: " + accountDto.getUsername());
        }
        final User user = new User();

        String password = PasswordGenerator.generatePassword(8);

        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setUsername(accountDto.getUsername());


        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(accountDto.getEmail());



        Role role = roleRepository.findById(accountDto.getRoleId()).orElseThrow(EntityNotFoundException::new);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);

        User persistedUser = userRepository.save(user);

        String url = getUrl(user);

        final String token = UUID.randomUUID().toString();
        this.createPasswordResetTokenForUser(persistedUser, token);
        sendCreateAccountEmail(url, token, persistedUser, password);

        return toUserResponse.convert(persistedUser);
    }

    private void sendCreateAccountEmail(final String contextPath, final String token, final User user, String password) {

        final String message = "Hi " + user.getUsername() + ", your account has been created successfully. \n Use username: " + user.getUsername() +
                " and password: " + password + " to login via " + url + " \n\n";
        emailService.sendSimpleMessage(user.getEmail(), "New Account", message + " \r\n" );
    }

    public String getUrl(User user) {

       return url;

    }

    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final User user) {
        final String resetMessage =  url + "/resetPassword/changePassword?token=" + token;
        final String message = "Reset Password";
        return emailService.sendSimpleMessage(user.getEmail(), "Reset Password", message + " \r\n" + resetMessage);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    private boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public User getUser(final String verificationToken) {
        final VerificationToken token = tokenRepository.findByToken(verificationToken);
        if (token != null) {
            return token.getUser();
        }
        return null;
    }

    @Override
    public void createVerificationTokenForUser(final User user, final String token) {
        final VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    @Override
    public VerificationToken generateNewVerificationToken(final String existingVerificationToken) {
        VerificationToken vToken = tokenRepository.findByToken(existingVerificationToken);
        vToken.updateToken(UUID.randomUUID()
                .toString());
        vToken = tokenRepository.save(vToken);
        return vToken;
    }

    @Override
    public void createPasswordResetTokenForUser(final User user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        myToken.setExpiryDate(dt);
        passwordTokenRepository.save(myToken);
    }

    @Override
    public User findUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUserByPasswordResetToken(final String token) {
        return Optional.ofNullable(passwordTokenRepository.findByToken(token).getUser());
    }

    @Override
    public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public Page<UserResponse> findAll(Pageable pageable, Long agentId) {
        return userRepository.findAllByAgentId(agentId, pageable)
                .map(toUserResponse::convert);
    }

    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(toUserResponse::convert);
    }

    @Override
    public List<UserResponse> findByRole(String role) {
        List<UserResponse> userResponseList = new ArrayList<>();
        List<User> userList = userRepository.findAll();

        for (User user : userList) {
            for (Role userRole : user.getRoles()
            ) {
                if (role.equalsIgnoreCase(userRole.getName())) {
                    userResponseList.add(toUserResponse.convert(user));
                }
            }
        }
        return userResponseList;
    }

    @Override
    public UserResponse activate(Long id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        user.setEnabled(true);
        user = userRepository.save(user);
        return toUserResponse.convert(user);
    }

    @Override
    public UserResponse deactivate(Long id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        user.setEnabled(false);
        user = userRepository.save(user);
        return toUserResponse.convert(user);
    }

    @Override
    public UserResponse enableUser(EnableAdminUserRequest request) {
        Role role = roleRepository.findByName(request.getRoleName())
                .orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(EntityNotFoundException::new);
        user.setEnabled(request.isEnabled());
        user.getRoles().clear();
        user.addRole(role);
        user = userRepository.save(user);
        return toUserResponse.convert(user);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void resendRegistrationToken(HttpServletRequest request, String existingToken) {
        final VerificationToken newToken = generateNewVerificationToken(existingToken);
        final User user = getUser(newToken.getToken());
        constructResendVerificationTokenEmail(getUrl(user), request.getLocale(), newToken, user);
    }

    @Override
    public GenericResponse resetPassword(HttpServletRequest request, String userEmail) {
        log.info("Inside RESET SERVice");
        final User user = findUserByEmail(userEmail);
        if (user != null) {
            final String token = UUID.randomUUID().toString();
            createPasswordResetTokenForUser(user, token);
            constructResetTokenEmail(getUrl(user), request.getLocale(), token, user);
            return new GenericResponse("You should receive a Password Reset Email shortly");
        } else {
            throw new InvalidRequestException("User with that email does not exist");
        }
    }

    @Override
    public GenericResponse savePassword(Locale locale, PasswordDto passwordDto) {
        log.info("I am here saving password");
        final String result = securityUserService.validatePasswordResetToken(passwordDto.getToken());

        log.info("Result: {}", result);
        if (result != null) {
            log.info("Token not found");
            return new GenericResponse("Token not found");
        }

        Optional<User> user = getUserByPasswordResetToken(passwordDto.getToken());
        if (user.isPresent()) {
            user.get().setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
            userRepository.save(user.get());
            log.info("Password reset successfully");
            passwordTokenRepository.delete(passwordTokenRepository.findByToken(passwordDto.getToken()));
            return new GenericResponse("Password reset successfully");
        } else {
            log.info("Invalid Token");
            return new GenericResponse("Invalid token");
        }
    }

    @Override
    public void changeUserPassword(ChangePasswordRequest passwordDto) {

        String username = getUsername();

        final User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RecordNotFoundException("User not found"));

        if (!checkIfValidOldPassword(user, passwordDto.getOldPassword())) {
            throw new InvalidRequestException("Invalid old Password");
        }
        user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
        userRepository.save(user);
    }

    private String getUsername() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    @Override
    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("User not found"));
        return toUserResponse.convert(user);
    }

    @Override
    public Page<UserResponse> findAllAdminUsers(Pageable pageable, String search) {
        if (isNull(search)) {
            return userRepository.findAllAdminUsers(pageable)
                    .map(toUserResponse::convert);
        } else {
            Specification<User> spec = (new CustomSpecificationTemplateImplBuilder<User>())
                    .buildSpecification(search);
            spec = spec.and(isAgentIdNull());
            return this.userRepository.findAll(spec, pageable)
                    .map(toUserResponse::convert);
        }
    }

    public static Specification<User> isAgentIdNull() {
        return (root, query, builder) -> builder.equal(root.get("agentId"), null);
    }

    public static Specification<User> isAgentIdEqual(Long agentId) {
        return (root, query, builder) -> builder.equal(root.get("agentId"), agentId);
    }

    public static Specification<User> isClientIdNull() {
        return (root, query, builder) -> builder.equal(root.get("clientId"), null);
    }

    public static Specification<User> isClientIdEqual(Long clientId) {
        return (root, query, builder) -> builder.equal(root.get("clientId"), clientId);
    }


    public static Specification<User> isWorkerIdNull() {
        return (root, query, builder) -> builder.equal(root.get("workerId"), null);
    }

    public static Specification<User> isWorkerIdEqual(Long workerId) {
        return (root, query, builder) -> builder.equal(root.get("workerId"), workerId);
    }



    @Override
    public UserSearchResponse findByUsername(String username) {

        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {

            UserResponse userResponse = toUserResponse.convert(optionalUser.get());

            return new UserSearchResponse(true, userResponse);
        }

        return new UserSearchResponse(false);
    }



    @Override
    public UserResponse registerNewUserFeignAccount(UserDto accountDto) {
        if (emailExists(accountDto.getEmail())) {
            throw new InvalidRequestException("There is an account with that email address: " + accountDto.getEmail());
        }
        if (usernameExists(accountDto.getUsername())) {
            throw new InvalidRequestException("There is an account with that email address: " + accountDto.getUsername());
        }
        final User user = new User();

        String password = PasswordGenerator.generatePassword(8);

        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setUsername(accountDto.getUsername());
        if ((accountDto.getAgentId()!=null) && (accountDto.getAgentId()>0)) {
            user.setAgentId(accountDto.getAgentId());
        }
        if ((accountDto.getClientId()!=null) && (accountDto.getClientId()>0)) {
            user.setClientId(accountDto.getClientId());

        }

        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(accountDto.getEmail());



        Role role = roleRepository.findById(accountDto.getRoleId()).orElseThrow(EntityNotFoundException::new);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);

        User persistedUser = userRepository.save(user);

        String url = getUrl(user);

        final String token = UUID.randomUUID().toString();
        this.createPasswordResetTokenForUser(persistedUser, token);
        sendCreateAccountEmail(url, token, persistedUser, password);

        return toUserResponse.convert(persistedUser);
    }

    private SimpleMailMessage constructResendVerificationTokenEmail(final String contextPath, final Locale locale,
                                                                    final VerificationToken newToken, final User user) {
        final String confirmationUrl = url + "/registrationConfirm.html?token=" + newToken.getToken();
        final String message = "We will send an email with a new registration token to your email account";
        return emailService.sendSimpleMessage(user.getEmail(), "Resend Registration Token", message + " " +
                "\r\n" + confirmationUrl);
    }


}
