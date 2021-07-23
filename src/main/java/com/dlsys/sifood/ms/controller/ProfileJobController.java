package com.dlsys.sifood.ms.controller;


import com.dlsys.sifood.ms.entity.ProfileJob;
import com.dlsys.sifood.ms.model.SearchModel;
import com.dlsys.sifood.ms.service.IProfileJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/profile")
public class ProfileJobController {

    @Autowired
    IProfileJobService profileJob;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> postProfileJob(@Valid @RequestBody ProfileJob profile, BindingResult result) {
        return profileJob.postPorfile(profile, result);
    }

    @RequestMapping(value = "/updateProfileJobInformation", method = RequestMethod.PUT)
    public ResponseEntity<?> putProfileJob(@Valid @RequestBody ProfileJob profile, BindingResult result) {
        return profileJob.putPorfile(profile, result);
    }

    @RequestMapping(value = "/getProfileJobInformation", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileJob(@RequestBody SearchModel profile) {
        return profileJob.getProfile(profile);
    }
}
