package com.kunal.ride_service.controller;

import com.kunal.ride_service.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

@SpringBootTest
public class UserControllerTests {

    private UserController userController;
    static HashMap<String,String> userParams;

    public UserControllerTests(){
        userController = new UserController();
        userParams = new HashMap<>();
        userParams.put(Constants.userId,"Kunal");
        userParams.put(Constants.x_coordinate,"0");
        userParams.put(Constants.y_coordinate,"0");
    }

    @Test
    public void userSuccessfullyCreatedTest(){
        assert(userController.create(userParams).getBody().equals("User Successfully created"));
    }

    @Test
    public void userDuplicateCreatedTest(){
        userController.create(userParams);
        assert(userController.create(userParams).getBody().equals("User already  exists"));
    }

    @Test
    public void userDoesNotExistsTest(){
        assert(userController.history(userParams).getBody().equals("User does not exists"));
    }

}
