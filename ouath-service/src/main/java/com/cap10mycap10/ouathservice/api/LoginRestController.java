package com.cap10mycap10.ouathservice.api;

import com.cap10mycap10.ouathservice.dto.UserLogin;
import com.cap10mycap10.ouathservice.dto.UserLoginRequest;
import com.cap10mycap10.ouathservice.entity.ResultDTO;
import com.cap10mycap10.ouathservice.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/v1")
public class LoginRestController {

    private final LoginService loginService;

    public LoginRestController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserLogin userLogin) throws IllegalAccessException {
        log.info("### Sending login Request");
        ResultDTO resultDTO = loginService.userLogin(userLogin);
        return ResponseEntity.ok(resultDTO);
    }

    @PostMapping("/client-login")
    public ResponseEntity<Object> login(@RequestBody UserLoginRequest request) {
        log.info("Sending Request");
        ResultDTO resultDTO = loginService.clientLogin(request);
        return ResponseEntity.ok(resultDTO);
    }

    @PostMapping("/refresh-token/{token}/{username}")
    public ResponseEntity<ResultDTO> refreshToken(@PathVariable("token") String token,
                                                  @PathVariable("username") String username) {
        log.info("Sending Request");
        ResultDTO resultDTO = loginService.refreshToken(token, username);
        return ResponseEntity.ok(resultDTO);
    }


}
