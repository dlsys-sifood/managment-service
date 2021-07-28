package com.dlsys.sifood.ms.service.impl;

import com.dlsys.sifood.ms.entity.Template;;
import com.dlsys.sifood.ms.model.TemplateModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface ITemplateService {
        public ResponseEntity<?> postTemplate(Template template, BindingResult result);
        public ResponseEntity<?> putTemplate(Template template, BindingResult result);
        public ResponseEntity<?> getTemplate(TemplateModel template);
}
