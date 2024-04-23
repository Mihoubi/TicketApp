package com.example.ticketapp.controller;

import com.example.ticketapp.entity.Bus;
import com.example.ticketapp.entity.Passenger;
import com.example.ticketapp.entity.User;
import com.example.ticketapp.service.BusService;
import com.example.ticketapp.service.PassengerService;
import com.example.ticketapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class MainController {
    private UserService userService;
    private PassengerService passengerService;
    private BusService busService;
    private boolean loggedIn;
    private Long userId;
    MainController(UserService userService, PassengerService passengerService, BusService busService) {
        this.passengerService = passengerService;
        this.busService = busService;
        this.userService = userService;
    }

    @GetMapping("/checkDB/{pageName}")
    public ResponseEntity<Boolean> checkedDB(@PathVariable(name = "pageName") String pageName, Model model) {
        boolean isExist = passengerService.checkAmount(userId, pageName);
        return ResponseEntity.ok(isExist);
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        if (loggedIn) {
            return "mainPage";
        } else {
            return "welcomePage";
        }
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        if (loggedIn) {
            double cash = passengerService.getCash(userId);
            String lastBus = passengerService.getBusName(userId);
            model.addAttribute("price", cash);
            model.addAttribute("lastBus", lastBus.toUpperCase());
            return "mainPage";
        } else {
            return "loginPage";
        }
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        loggedIn = false;
        return "redirect:/";
    }

    @GetMapping("/profilePage")
    public String profilePage(Model model) {
        User sampleUser = userService.getById(userId);
        Passenger samplePassenger = passengerService.getById(userId);

        model.addAttribute("userEmail", sampleUser.getName());
        model.addAttribute("userName", samplePassenger.getName());
        model.addAttribute("userPrice", samplePassenger.getPrice());
        model.addAttribute("userLastBus", samplePassenger.getLastBus());

        return "profilePage";
    }

    @PostMapping("/submitForm")
    public String handleFormSubmission(@RequestParam("email") String email,
                                       @RequestParam("password") String password,
                                       Model model) {
        String dbPassword = userService.getPasswordByUserName(email);
        if (dbPassword == null) {
            return "loginPage";
        } else {
            model.addAttribute("isLogin", dbPassword.equals(password));
            if (dbPassword.equals(password)) {
                loggedIn = true;
                userId = userService.getIdByEmail(email);
                return "redirect:/login";
            } else {
                return "loginPage";
            }
        }
    }

    @PostMapping("/registerForm")
    public String registerUser(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                               @RequestParam("name") String name) {
        User sampleUser = new User();
        sampleUser.setName(email);
        sampleUser.setPassword(password);
        userService.save(sampleUser);
        Passenger samplePassenger = new Passenger();
        samplePassenger.setName(name);
        samplePassenger.setPrice(100);
        samplePassenger.setLastBus("unknown");
        samplePassenger.setUserId(sampleUser.getId());
        passengerService.save(samplePassenger);
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        return "registerPage";
    }

    @RequestMapping("/pay/{pageName}")
    @Transactional
    public String payBus(@PathVariable(name = "pageName") String pageName, Model model) {
        passengerService.payForBus(pageName, userId);
        return "redirect:/login";
    }

    @GetMapping("/loadMoney")
    public String loadMoney() {
        passengerService.loadMoney(userId);
        return "redirect:/profilePage";
    }

    @GetMapping("/{pageName}")
    public String showPage(@PathVariable("pageName") String pageName, Model model) {
        if (loggedIn) {
            String pageTitle = "";
            String message = "";

            List<Bus> listBus = busService.getByName(pageName);

            pageTitle = "Page " + pageName.toUpperCase();
            message = "Welcome to Page " + pageName.toUpperCase();

            model.addAttribute("pageTitle", pageTitle);
            model.addAttribute("message", message);

            if (!listBus.isEmpty()) {
                model.addAttribute("price", listBus.get(0).getLinePrice());
            }

            return "busInfoPage";
        } else {
            return "loginPage";
        }
    }

}
