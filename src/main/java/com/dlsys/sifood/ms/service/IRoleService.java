package com.dlsys.sifood.ms.service;

import com.dlsys.sifood.ms.entity.Role;
import com.dlsys.sifood.ms.model.SearchModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface IRoleService {
    public ResponseEntity<?> postRole(Role role, BindingResult result);
    public ResponseEntity<?> putRole(Role role, BindingResult result);
    public ResponseEntity<?> getRole(SearchModel profile);
}
