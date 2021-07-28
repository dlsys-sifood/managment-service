package com.dlsys.sifood.ms.service;

import com.dlsys.sifood.ms.dao.ITemplateDao;
import com.dlsys.sifood.ms.entity.Template;
import com.dlsys.sifood.ms.model.TemplateModel;
import com.dlsys.sifood.ms.response.EntityResponse;
import com.dlsys.sifood.ms.service.impl.ITemplateService;
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

@Service
public class TemplateService implements ITemplateService {

    private static final String BADREQUESTCODE = HttpStatus.BAD_REQUEST.toString();
    private static final String BADREQUESTDESCRIPTION = "BAD REQUEST";

    private static final String OKREQUESTCODE = HttpStatus.OK.toString();
    private static final String OKREQUESTDESCRIPTION = "OK";

    @Autowired
    ITemplateDao templateDao;

    @Override
    public ResponseEntity<?> postTemplate(Template template, BindingResult result) {
        if(result.hasErrors()){
            return EntityResponse.getErrorsFieldResponse(result);
        }
        try {
            List<Template> templateExist = templateDao.findByName(template.getName());
            if (templateExist.isEmpty()) {
                templateDao.save(template);
            } else {
                return EntityResponse.getErrorCustomMessage("el dato ya se encuentra registado");
            }
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
        return EntityResponse.getSuccessfullTemplate(template);
    }

    @Override
    public ResponseEntity<?> putTemplate(Template template, BindingResult result) {
        if(result.hasErrors()){
            return EntityResponse.getErrorsFieldResponse(result);
        }
        try {
            List<Template> templateExist = templateDao.findByName(template.getName());
            if (templateExist.isEmpty()) {
                templateDao.save(template);
            } else {
                return EntityResponse.getErrorCustomMessage("el dato ya se encuentra registado");
            }
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
        return EntityResponse.getSuccessfullTemplate(template);
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
            return EntityResponse.getErrorCustomMessage("consutal no encontrada");
        }
        return EntityResponse.getSuccessfullListTemplate(response);
    }
}
