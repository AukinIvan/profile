package com.testtask.profile.controller;

import com.testtask.profile.exception.RestException;
import com.testtask.profile.model.Profile;
import com.testtask.profile.repository.ProfileRepository;
import com.testtask.profile.to.EmailTo;
import com.testtask.profile.to.ProfileTo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @PostMapping("/set")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header"),
    })
    @ApiOperation(value = "Создать профиль")
    public Map<String, Integer> set(@Valid @RequestBody ProfileTo profileTo) {
        Profile profile = new Profile(profileTo.getName(), profileTo.getEmail(), profileTo.getAge());
        profileRepository.save(profile);
        Profile newProfile = profileRepository.findProfileByEmail(profile.getEmail());
        return Collections.singletonMap("idUser", newProfile.getId());
    }

    @GetMapping("/last")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header"),
    })
    @ApiOperation(value = "Получить последний созданный профиль")
    public Profile lastProfile() {
        return profileRepository.findFirstByOrderByCreatedDesc();
    }

    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header"),
    })
    @ApiOperation(value = "Список профилей")
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    @GetMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header"),
    })
    @ApiOperation(value = "Получить профиль по id")
    public Profile getProfile(@PathVariable int id) {
        return profileRepository.findById(id).orElseThrow(RestException.NotFoundException::new);
    }

    @PostMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header"),
    })
    @ApiOperation(value = "Получить профиль по email")
    public Profile getProfileByEmail(@RequestBody EmailTo email) {
        return profileRepository.findByEmailIgnoreCase(email.getEmail()).orElseThrow(RestException.NotFoundException::new);
    }
}
