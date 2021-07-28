package com.dlsys.sifood.ms.service.profileJob;

import com.dlsys.sifood.ms.dao.IProfileJobDao;
import com.dlsys.sifood.ms.dto.GenericResponse;
import com.dlsys.sifood.ms.dto.ProfileJobResponse;
import com.dlsys.sifood.ms.entity.ProfileJob;
import com.dlsys.sifood.ms.model.SearchModel;
import com.dlsys.sifood.ms.service.GenericService;
import com.dlsys.sifood.ms.service.ResponseService;
import com.dlsys.sifood.ms.service.profileJob.IProfileJobService;
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

    @Autowired
    IProfileJobDao profileDao;

    @Override
    public ResponseEntity<?> postPorfile(ProfileJob profile, BindingResult result) {
        if(result.hasErrors()){
            return GenericService.getErrorsFieldResponse(result);
        }
        try {
            List<ProfileJob> profileExist = profileDao.findByName(profile.getName());
            if (profileExist.isEmpty()) {
                profileDao.save(profile);
            } else {
                return GenericService.getErrorCustomMessage("el dato ya se encuentra registado");
            }
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
        return GenericService.getSuccessfullProfileJob(profile);
    }

    @Override
    public ResponseEntity<?> putPorfile(ProfileJob profile, BindingResult result) {
        if(result.hasErrors()){
            return GenericService.getErrorsFieldResponse(result);
        }
        try {
            List<ProfileJob> profileExist = profileDao.findByName(profile.getName());
            if (profileExist.isEmpty()) {
                profileDao.save(profile);
            } else {
                return GenericService.getErrorCustomMessage("el dato ya se encuentra registado");
            }
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
        return GenericService.getSuccessfullProfileJob(profile);
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
            return GenericService.getErrorCustomMessage("perfil no encontrado");
        }
        return GenericService.getSuccessfullListProfileJob(response);
    }

}
