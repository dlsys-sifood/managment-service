package com.dlsys.sifood.ms.service;

import com.dlsys.sifood.ms.dao.ITemplateDao;
import com.dlsys.sifood.ms.dto.GenericResponse;
import com.dlsys.sifood.ms.dto.RoleResponse;
import com.dlsys.sifood.ms.dto.TemplateResponse;
import com.dlsys.sifood.ms.entity.Role;
import com.dlsys.sifood.ms.entity.Template;
import com.dlsys.sifood.ms.model.TemplateModel;
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
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TemplateService implements ITemplateService{

    private static final String BADREQUESTCODE = HttpStatus.BAD_REQUEST.toString();
    private static final String BADREQUESTDESCRIPTION = "BAD REQUEST";

    private static final String OKREQUESTCODE = HttpStatus.OK.toString();
    private static final String OKREQUESTDESCRIPTION = "OK";

    @Autowired
    ITemplateDao templateDao;

    @Override
    public ResponseEntity<?> postTemplate(Template template, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<Map<String, Object>>(ResponseService
                    .responseGeneric(new GenericResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                            result.getFieldErrors().stream()
                                    .map(e -> "el campo: " + e.getField() + " " + e.getDefaultMessage())
                                    .collect(Collectors.toList())))
                    , HttpStatus.BAD_REQUEST);
        }

        try {
            List<Template> templateExist = templateDao.findByName(template.getName());
            if (templateExist.isEmpty()) {
                templateDao.save(template);
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
                .responseTemplate(new TemplateResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("el role se a guardado"), template))
                , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> putTemplate(Template template, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<Map<String, Object>>(ResponseService
                    .responseGeneric(new GenericResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                            result.getFieldErrors().stream()
                                    .map(e -> "el campo: " + e.getField() + " " + e.getDefaultMessage())
                                    .collect(Collectors.toList())))
                    , HttpStatus.BAD_REQUEST);
        }

        try {
            List<Template> templateExist = templateDao.findByName(template.getName());
            if (templateExist.isEmpty()) {
                templateDao.save(template);
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
            templateDao.save(template);
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(ResponseService
                .responseTemplate(new TemplateResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("el rol se a actualizado"), template))
                , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getTemplate(TemplateModel template) {
        List<Template> response = new ArrayList<>();

        try {
            response = templateDao.findAll(new Specification<Template>() {
                @Override
                public Predicate toPredicate(Root<Template> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                    Predicate p = cb.conjunction();
                    if(!template.getName().isEmpty()) {
                        p = cb.and(p, cb.like(root.get("name"), "%" + template.getName() + "%"));
                    }
                    if(!template.getUrl().isEmpty()) {
                        p = cb.and(p, cb.like(root.get("url"), "%" + template.getUrl() + "%"));
                    }
                    if(!template.getId().isEmpty()) {
                        p = cb.and(p, cb.equal(root.get("id"),  template.getId()));
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

        return new ResponseEntity<>(ResponseService
                .responseTemplate(new TemplateResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("el rol se a actualizado"), response))
                , HttpStatus.OK);
    }
}
