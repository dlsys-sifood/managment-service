package com.dlsys.sifood.ms.service;

import com.dlsys.sifood.ms.dao.IProfileJobDao;
import com.dlsys.sifood.ms.dto.GenericResponse;
import com.dlsys.sifood.ms.dto.ProfileJobResponse;
import com.dlsys.sifood.ms.entity.ProfileJob;
import com.dlsys.sifood.ms.model.SearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProfileJobService implements IProfileJobService {

    private static final String BADREQUESTCODE = HttpStatus.BAD_REQUEST.toString();
    private static final String BADREQUESTDESCRIPTION = "BAD REQUEST";

    private static final String OKREQUESTCODE = HttpStatus.OK.toString();
    private static final String OKREQUESTDESCRIPTION = "OK";

    @Autowired
    IProfileJobDao profileDao;

    @Override
    public ResponseEntity<?> postPorfile(ProfileJob profile, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<Map<String, Object>>(ResponseService
                    .responseGeneric(new GenericResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                            result.getFieldErrors().stream()
                                    .map(e -> "el campo: " + e.getField() + " " + e.getDefaultMessage())
                                    .collect(Collectors.toList())))
                    , HttpStatus.BAD_REQUEST);
        }

        try {
            List<ProfileJob> profileExist = profileDao.findByName(profile.getName());
            if (profileExist.isEmpty()) {
                profileDao.save(profile);
            } else {
                return new ResponseEntity<Map<String, Object>>(ResponseService
                        .responseGeneric(new GenericResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                                GenericResponse.toList("el dato ya se encuentra registado")))
                        , HttpStatus.BAD_REQUEST);
            }
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(ResponseService
                .responsProfile(new ProfileJobResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("el profile se a guardado"), profile))
                , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> putPorfile(ProfileJob profile, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<Map<String, Object>>(ResponseService
                    .responseGeneric(new GenericResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                            result.getFieldErrors().stream()
                                    .map(e -> "el campo: " + e.getField() + " " + e.getDefaultMessage())
                                    .collect(Collectors.toList())))
                    , HttpStatus.BAD_REQUEST);
        }

        try {
            List<ProfileJob> profileExist = profileDao.findByName(profile.getName());
            if (profileExist.isEmpty()) {
                profileDao.save(profile);
            } else {
                return new ResponseEntity<Map<String, Object>>(ResponseService
                        .responseGeneric(new GenericResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                                GenericResponse.toList("el dato ya se encuentra registado")))
                        , HttpStatus.BAD_REQUEST);
            }
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }

        try {
            profileDao.save(profile);
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(new ProfileJobResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                GenericResponse.toList("el profile se a guardado"), profile), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getProfile(SearchModel profile) {
        List<ProfileJob> response = new ArrayList<>();

        try {
            response = profileDao.findAll(new Specification<ProfileJob>() {
                @Override
                public Predicate toPredicate(Root<ProfileJob> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                    Predicate p = cb.conjunction();
                    if(!profile.getName().isEmpty()) {
                        p = cb.and(p, cb.like(root.get("name"), "%" + profile.getName() + "%"));
                    }
                    if(!profile.getId().isEmpty()) {
                        p = cb.and(p, cb.equal(root.get("id"), profile.getId()));
                    }
                    return p;
                }
            });
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }

        if(response.isEmpty()){
            return new ResponseEntity<Map<String, Object>>(ResponseService
                    .responseGeneric(new GenericResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                            GenericResponse.toList("perfil no encontrado")))
                    , HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new ProfileJobResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                GenericResponse.toList("consulta encontrada"), response), HttpStatus.OK);
    }

}
