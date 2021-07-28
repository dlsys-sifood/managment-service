package com.dlsys.sifood.ms.service.role;

import com.dlsys.sifood.ms.dao.IRoleDao;
import com.dlsys.sifood.ms.dto.GenericResponse;
import com.dlsys.sifood.ms.dto.RoleResponse;

import com.dlsys.sifood.ms.entity.Role;
import com.dlsys.sifood.ms.model.SearchModel;
import com.dlsys.sifood.ms.service.GenericService;
import com.dlsys.sifood.ms.service.ResponseService;
import com.dlsys.sifood.ms.service.role.IRoleService;
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
public class RoleService implements IRoleService {

    @Autowired
    IRoleDao roleDao;

    @Override
    public ResponseEntity<?> postRole(Role role, BindingResult result) {
        if(result.hasErrors()){
            return GenericService.getErrorsFieldResponse(result);
        }
        try {
            List<Role> roleExist = roleDao.findByName(role.getName());
            if (roleExist.isEmpty()) {
                roleDao.save(role);
            } else {
                return GenericService.getErrorCustomMessage("el dato ya se encuentra registado");
            }
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
        return GenericService.getSuccessfullRole(role);
    }

    @Override
    public ResponseEntity<?> putRole(Role role, BindingResult result) {
        if(result.hasErrors()){
            return GenericService.getErrorsFieldResponse(result);
        }
        try {
            List<Role> roleExist = roleDao.findByName(role.getName());
            if (roleExist.isEmpty()) {
                roleDao.save(role);
            } else {
                return GenericService.getErrorCustomMessage("el dato ya se encuentra registado");
            }
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
        return GenericService.getSuccessfullRole(role);
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
             return GenericService.getErrorCustomMessage("consutal no encontrada");
        }
        return GenericService.getSuccessfullListRole(response);
    }
}
