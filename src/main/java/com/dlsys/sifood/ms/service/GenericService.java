package com.dlsys.sifood.ms.service;

import com.dlsys.sifood.ms.dto.GenericResponse;
import com.dlsys.sifood.ms.dto.ProfileJobResponse;
import com.dlsys.sifood.ms.dto.RoleResponse;
import com.dlsys.sifood.ms.dto.TemplateResponse;
import com.dlsys.sifood.ms.entity.ProfileJob;
import com.dlsys.sifood.ms.entity.Role;
import com.dlsys.sifood.ms.entity.Template;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GenericService {

    private static final String BADREQUESTCODE = HttpStatus.BAD_REQUEST.toString();
    private static final String BADREQUESTDESCRIPTION = "BAD REQUEST";

    private static final String OKREQUESTCODE = HttpStatus.OK.toString();
    private static final String OKREQUESTDESCRIPTION = "OK";

    public static ResponseEntity<?> getErrorsFieldResponse(BindingResult result){
        return new ResponseEntity<Map<String, Object>>(ResponseService
                .responseGeneric(new GenericResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                        result.getFieldErrors().stream()
                                .map(e -> "el campo: " + e.getField() + " " + e.getDefaultMessage())
                                .collect(Collectors.toList())))
                , HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<?> getErrorCustomMessage(String custom){
        return new ResponseEntity<Map<String, Object>>(ResponseService
                .responseGeneric(new GenericResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                        GenericResponse.toList(custom)))
                , HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<?> getSuccessfullListProfileJob(List<ProfileJob> response){
        return new ResponseEntity<>(new ProfileJobResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                GenericResponse.toList("consulta encontrada"), response), HttpStatus.OK);
    }

    public static ResponseEntity<?> getSuccessfullProfileJob(ProfileJob response){
        return new ResponseEntity<>(ResponseService
                .responsProfile(new ProfileJobResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("guardado exitoso"), response))
                , HttpStatus.OK);
    }

    public static ResponseEntity<?> getSuccessfullListRole(List<Role> response){
        return new ResponseEntity<>(new RoleResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                GenericResponse.toList("consulta encontrada"), response), HttpStatus.OK);
    }

    public static ResponseEntity<?> getSuccessfullRole(Role response){
        return new ResponseEntity<>(ResponseService
                .responsRole(new RoleResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("el role se a guardado"), response))
                , HttpStatus.OK);
    }

    public static ResponseEntity<?> getSuccessfullListTemplate(List<Template> response){
        return new ResponseEntity<>(ResponseService
                .responseTemplate(new TemplateResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("consulta encontrada"), response)), HttpStatus.OK);
    }

    public static ResponseEntity<?> getSuccessfullTemplate(Template response){
        return new ResponseEntity<>(ResponseService
                .responseTemplate(new TemplateResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("se a guardado exitosamente"), response))
                , HttpStatus.OK);
    }
}
