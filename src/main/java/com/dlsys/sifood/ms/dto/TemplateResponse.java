package com.dlsys.sifood.ms.dto;

import com.dlsys.sifood.ms.entity.Template;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TemplateResponse extends GenericResponse {
    private Template template;
    private List<Template> templates;

    public TemplateResponse(String statusCode, String statusResponse, List<String> description, Template template) {
        super(statusCode, statusResponse, description);
        this.template = template;
    }

    public TemplateResponse(String statusCode, String statusResponse, List<String> description, List<Template> templates) {
        super(statusCode, statusResponse, description);
        this.templates = templates;
    }
}
