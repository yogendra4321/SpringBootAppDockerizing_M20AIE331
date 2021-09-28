package com.cloud.ass.demo.controller;

import com.cloud.ass.demo.entity.request.UserDetails;
import com.cloud.ass.demo.service.LoginService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "user info",tags = "User Operations")
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user data"),
            @ApiResponse(code = 400, message = "No username found.")
    })
    @ApiOperation(value = "Check if the user exist", response = String.class)
    @PostMapping("/user")
    public ResponseEntity<String> getUserDetails(@ApiParam(value = "username to search", required = true)
                                                 @Validated @RequestBody UserDetails userDetails) {
        String username = loginService.checkUsername(userDetails.getUsername()).getName();
        return new ResponseEntity<>("Hey " + username, HttpStatus.OK);
    }

    @ApiOperation(value = "Add new user details", response = String.class)
    @PostMapping("/user/add")
    public ResponseEntity<String> addUserDetails( @RequestBody UserDetails userDetails) {
        String username = loginService.addUser(userDetails.getUsername(), userDetails.getName()).getName();
        return new ResponseEntity<>("Hey " + username, HttpStatus.OK);
    }
}
