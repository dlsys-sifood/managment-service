package com.dlsys.sifood.ms.controller;

import com.dlsys.sifood.ms.entity.Template;
import com.dlsys.sifood.ms.model.TemplateModel;
import com.dlsys.sifood.ms.service.impl.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/template")
public class TemplateController {

    @Autowired
    ITemplateService templateService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> postProfileJob(@Valid @RequestBody Template template, BindingResult result) {
        return templateService.postTemplate(template, result);
    }

    @RequestMapping(value = "/updateTemplateInformation", method = RequestMethod.PUT)
    public ResponseEntity<?> putProfileJob(@Valid @RequestBody Template template, BindingResult result) {
        return templateService.postTemplate(template, result);
    }

    @RequestMapping(value = "/getTemplateInformation", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileJob(@RequestBody TemplateModel template) {
        return templateService.getTemplate(template);
    }
}
