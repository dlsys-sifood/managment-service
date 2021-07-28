package com.dlsys.sifood.ms.controller;


import com.dlsys.sifood.ms.entity.Role;
import com.dlsys.sifood.ms.model.SearchModel;
import com.dlsys.sifood.ms.service.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> postProfileJob(@Valid @RequestBody Role role, BindingResult result) {
        return roleService.postRole(role, result);
    }

    @RequestMapping(value = "/updateProfileJobInformation", method = RequestMethod.PUT)
    public ResponseEntity<?> putProfileJob(@Valid @RequestBody Role role, BindingResult result) {
        return roleService.putRole(role, result);
    }

    @RequestMapping(value = "/getProfileJobInformation", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileJob(@RequestBody SearchModel profile) {
        return roleService.getRole(profile);
    }

}
