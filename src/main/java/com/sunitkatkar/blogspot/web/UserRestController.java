package com.sunitkatkar.blogspot.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunitkatkar.blogspot.util.TenantContextHolder;
import com.sunitkatkar.blogspot.tenant.model.User;
import com.sunitkatkar.blogspot.tenant.service.UserService;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{tenantId}")
    @ResponseBody
    public List<String> getUsersForTenant(
            @PathVariable("tenantId") String tenantId) {
        TenantContextHolder.setTenantId(tenantId);
        List<User> users = userService.findAllUsers();
        List<String> userList = users.stream().map(result -> result.toString())
                .collect(Collectors.toList());
        return userList;
    }

}
