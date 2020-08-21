package com.testtask.profile.controller;

import com.testtask.profile.config.jwt.JwtProvider;
import com.testtask.profile.model.User;
import com.testtask.profile.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
public class RootController {
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Autowired
    public RootController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    @ApiOperation(value = "Регистрация пользователя")
    public void regUser(@Valid @RequestBody User user) {
        userService.saveUser(user);
    }

    @PostMapping(value = "/auth")
    @ApiOperation(value = "Аутентификация пользователя", notes = "Проверка пользователя, генерация токена с сохранением в базу, выдача токена пользователю")
    public String authUser(@Valid @RequestBody User user) {
        User findUser = userService.findByLoginAndPassword(user.getName(), user.getPassword());
        String token = jwtProvider.generateToken(findUser.getName());
        findUser.getToken().add(token);
        userService.saveUser(findUser);
        return token;
    }

    @GetMapping("/exit")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header"),
    })
    @ApiOperation(value = "Завершение работы", notes = "Производит закрытия приложение с редиректом на страницу /exit-success")
    public void exit(HttpServletResponse response) throws IOException {
        response.sendRedirect("exit-success");
    }

    @GetMapping("/exit-success")
    @ApiOperation(value = "Успешное закрытие", notes = "Страница подтверждает завершение работы приложения")
    public ResponseEntity<String> exitSuccess() {
        return ResponseEntity.ok("App success exit");
    }
}
