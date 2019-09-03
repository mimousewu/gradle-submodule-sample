package com.thoughtworks.backend.input.restful;

import com.thoughtworks.backend.web.SecurityHead;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Account"})
@SwaggerDefinition(tags = @Tag(
        name = "Web Site",
        description = "account api"))
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class LoginController {
    @SecurityHead
    @ApiOperation(value = "login by username and password")
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void loginAndRegister(@RequestParam String username, String password) {
    }
}
