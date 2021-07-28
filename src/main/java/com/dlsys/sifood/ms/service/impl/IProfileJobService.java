package com.dlsys.sifood.ms.service.impl;

import com.dlsys.sifood.ms.entity.ProfileJob;
import com.dlsys.sifood.ms.model.SearchModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface IProfileJobService {
    public ResponseEntity<?> postPorfile(ProfileJob profile, BindingResult result);
    public ResponseEntity<?> putPorfile(ProfileJob profile, BindingResult result);
    public ResponseEntity<?> getProfile(SearchModel profile);
}
