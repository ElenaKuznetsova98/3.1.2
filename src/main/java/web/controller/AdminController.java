package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String listUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("user", user);
        return "admin";
    }


    @PostMapping(value = "add-user")
    public String addUser(@ModelAttribute("user") User user, @RequestParam(value = "nameRoles") String [] nameRoles) {
        user.setRoles(roleService.getSetOfRoles(nameRoles));
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @PostMapping(value = "/edit")
    public String editUser(@ModelAttribute User user, @RequestParam(value = "nameRoles") String [] nameRoles) {
        user.setRoles(roleService.getSetOfRoles(nameRoles));
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/remove")
    public String removeUser(@ModelAttribute("user") User user) {
        userService.removeUserById(user.getId());
        return "redirect:/admin";
    }
}
