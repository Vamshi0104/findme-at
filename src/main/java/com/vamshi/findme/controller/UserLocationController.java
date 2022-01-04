package com.vamshi.findme.controller;

import com.vamshi.findme.EncryptionProcess;
import com.vamshi.findme.model.UserLocation;
import com.vamshi.findme.repository.UserLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserLocationController {
    final String secretKey = "u-can't-get-me";
    @Autowired
    UserLocationRepository userLocationRepository;

    @GetMapping("")
    public String viewHomePage(Model model) {
        model.addAttribute("user", new UserLocation());
        return "index";
    }
    @PostMapping("/process_register")
    public String processRegister(UserLocation user) {
        String encryptedPhone = EncryptionProcess.encrypt(user.getPhone(), secretKey) ;
        user.setPhone(encryptedPhone);
        String encryptedPassword = EncryptionProcess.encrypt(user.getSaltKey().substring(11), secretKey) ;
        user.setSaltKey(encryptedPassword);
        String encryptedLocation = EncryptionProcess.encrypt(user.getLocation(), secretKey) ;
        user.setLocation(encryptedLocation);
        String encryptedTitle = EncryptionProcess.encrypt(user.getTitle(),secretKey);
        user.setTitle(encryptedTitle);
        if(userLocationRepository.findAll().contains(user))
            return "duplicate_record";
        userLocationRepository.save(user);
        return "register_success";
    }
    @PostMapping("/get_location")
    public String getUserLocation(@RequestParam(value="savedPhone", required = true) String phone,@RequestParam(value = "savedPassword",required = true) String pass,Model model){
        boolean flag=false;
        String encryptedPhone = EncryptionProcess.encrypt(phone, secretKey);
        String encryptedPassword = EncryptionProcess.encrypt(pass, secretKey);
        List<UserLocation> listUsers = userLocationRepository.findAll();
        model.addAttribute("gmap","https://www.google.com/maps/dir/?api=1&destination=");
        String a=listUsers.stream()
                .filter(i->i.getPhone().equals(encryptedPhone) && i.getSaltKey().equals(encryptedPassword))
                .map(i->i.getLocation()).collect(Collectors.toList()).get(0);
        if(a.length()==0)
            return "error";
        String decryptedLocation = EncryptionProcess.decrypt(a, secretKey);
        model.addAttribute("location", decryptedLocation);
        return "getLocation";
    }
    @GetMapping("/share_location")
    public String shareLocation(){
        return "sharing_location";
    }
    @PostMapping("/share_my_location")
    public String shareMyLocation(@RequestParam(value="savedPhone", required = true) String phone,@RequestParam(value = "savedPassword",required = true) String pass,Model model){
        boolean flag=false;
        String encryptedPhone = EncryptionProcess.encrypt(phone, secretKey);
        String encryptedPassword = EncryptionProcess.encrypt(pass, secretKey);
        List<UserLocation> listUsers = userLocationRepository.findAll();
        model.addAttribute("share_gmap","https://www.google.com/maps/dir/?api=1&destination=");
        String a=listUsers.stream()
                .filter(i->i.getPhone().equals(encryptedPhone) && i.getSaltKey().equals(encryptedPassword))
                .map(i->i.getLocation()).collect(Collectors.toList()).get(0);
        String b=listUsers.stream()
                .filter(i->i.getPhone().equals(encryptedPhone) && i.getSaltKey().equals(encryptedPassword))
                .map(i->i.getTitle()).collect(Collectors.toList()).get(0);
        if(a.length()==0 || b.length()==0)
            return "error";
        String decryptedTitle = EncryptionProcess.decrypt(b, secretKey);
        String decryptedLocation = EncryptionProcess.decrypt(a, secretKey);
        model.addAttribute("share_location", decryptedLocation);
        model.addAttribute("share_title", decryptedTitle);
        return "share_location_page";
    }
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<UserLocation> listUsers = userLocationRepository.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }
    @GetMapping("/del_all_users_tables")
    public String delUsersTable(){
        userLocationRepository.deleteAll();
        return "index";
    }
}
