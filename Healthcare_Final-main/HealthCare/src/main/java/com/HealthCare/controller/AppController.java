package com.HealthCare.controller;

import com.HealthCare.entity.Appointment;
import com.HealthCare.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AppController {

    @Autowired
    private AppointmentRepository repository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/make_an_appointment")
    public String showAppointmentForm() {
        return "make_an_appointment";
    }

    @PostMapping("/make_an_appointment")
    public String handleAppointmentForm(
            @RequestParam String name,
            @RequestParam String location,
            @RequestParam String mobile,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (mobile.length() != 10) {
            model.addAttribute("error_message", "Mobile number must be 10 digits long.");
            model.addAttribute("name", name);
            model.addAttribute("location", location);
            return "make_an_appointment";
        }

        Appointment appointment = new Appointment();
        appointment.setName(name);
        appointment.setLocation(location);
        appointment.setMobile(mobile);
        repository.save(appointment);

        redirectAttributes.addFlashAttribute("success_message", "Your response has been submitted!");
        return "redirect:/submitted";
    }

    @GetMapping("/submitted")
    public String submitted() {
        return "submitted";
    }

    @GetMapping("/thank_you")
    public String thankYou() {
        return "thankyou";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
