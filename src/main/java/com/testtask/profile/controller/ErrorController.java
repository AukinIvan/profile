package com.testtask.profile.controller;

import com.testtask.profile.model.Error;
import com.testtask.profile.repository.ErrorRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorController {
    private final ErrorRepository errorRepository;

    @Autowired
    public ErrorController(ErrorRepository errorRepository) {
        this.errorRepository = errorRepository;
    }

    @GetMapping("/last")
    @ApiOperation(value = "Получить последнюю ошибку")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header"),
    })
    public Error getLastError() {
        return errorRepository.findFirstByOrderByCreatedDesc();
    }
}
