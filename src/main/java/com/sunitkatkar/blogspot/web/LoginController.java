package com.sunitkatkar.blogspot.web;

import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sunitkatkar.blogspot.tenant.model.CustomUserDetails;
 

@Controller
public class LoginController {

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        getLoggedInUsername().ifPresent(f -> {
            model.addAttribute("userName", f);
        });
        getTenantName().ifPresent(d -> {
            model.addAttribute("tenantName", d);
        });

        return "index";
    }

    @RequestMapping("/user/index")
    public String userIndex(Model model) {
        getLoggedInUsername().ifPresent(f -> {
            model.addAttribute("userName", f);
        });
        getTenantName().ifPresent(d -> {
            model.addAttribute("tenantName", d);
        });
        return "user/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    private Optional<String> getLoggedInUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = null;
        if (auth != null && !auth.getClass().equals(AnonymousAuthenticationToken.class)) {
            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            userName = userDetails.getUsername();
        }

        return Optional.ofNullable(userName);
    }

    private Optional<String> getTenantName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String tenantName = null;
        if (auth != null && !auth.getClass().equals(AnonymousAuthenticationToken.class)) {
            // User user = (User) auth.getPrincipal();
            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            tenantName = userDetails.getTenant();
        }
        return Optional.ofNullable(tenantName);
    }
}
