package com.dlsys.sifood.ms.service;

import com.dlsys.sifood.ms.dao.IRoleDao;
import com.dlsys.sifood.ms.dto.GenericResponse;
import com.dlsys.sifood.ms.dto.RoleResponse;

import com.dlsys.sifood.ms.entity.Role;
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
public class RoleService implements IRoleService{

    private static final String BADREQUESTCODE = HttpStatus.BAD_REQUEST.toString();
    private static final String BADREQUESTDESCRIPTION = "BAD REQUEST";

    private static final String OKREQUESTCODE = HttpStatus.OK.toString();
    private static final String OKREQUESTDESCRIPTION = "OK";

    @Autowired
    IRoleDao roleDao;

    @Override
    public ResponseEntity<?> postRole(Role role, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<Map<String, Object>>(ResponseService
                    .responseGeneric(new GenericResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                            result.getFieldErrors().stream()
                                    .map(e -> "el campo: " + e.getField() + " " + e.getDefaultMessage())
                                    .collect(Collectors.toList())))
                    , HttpStatus.BAD_REQUEST);
        }

        try {
            List<Role> roleExist = roleDao.findByName(role.getName());
            if (roleExist.isEmpty()) {
                roleDao.save(role);
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
                .responsRole(new RoleResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("el role se a guardado"), role))
                , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> putRole(Role role, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<Map<String, Object>>(ResponseService
                    .responseGeneric(new GenericResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                            result.getFieldErrors().stream()
                                    .map(e -> "el campo: " + e.getField() + " " + e.getDefaultMessage())
                                    .collect(Collectors.toList())))
                    , HttpStatus.BAD_REQUEST);
        }

        try {
            List<Role> profileExist = roleDao.findByName(role.getName());
            if (profileExist.isEmpty()) {
                roleDao.save(role);
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
            roleDao.save(role);
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(ResponseService
                .responsRole(new RoleResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("el rol se a actualizado"), role))
                , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getRole(SearchModel role) {
        List<Role> response = new ArrayList<>();

        try {
            response = roleDao.findAll(new Specification<Role>() {
                @Override
                public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                    Predicate p = cb.conjunction();
                    if(!role.getName().isEmpty()) {
                        p = cb.and(p, cb.like(root.get("name"), "%" + role.getName() + "%"));
                    }
                    if(!role.getId().isEmpty()) {
                        p = cb.and(p, cb.equal(root.get("id"), role.getId()));
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

        return new ResponseEntity<>(new RoleResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                GenericResponse.toList("consulta encontrada"), response), HttpStatus.OK);
    }
}
