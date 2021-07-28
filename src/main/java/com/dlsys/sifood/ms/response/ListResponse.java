package com.dlsys.sifood.ms.response;

import com.dlsys.sifood.ms.dto.GenericResponse;
import com.dlsys.sifood.ms.dto.ProfileJobResponse;
import com.dlsys.sifood.ms.dto.RoleResponse;
import com.dlsys.sifood.ms.dto.TemplateResponse;
import com.dlsys.sifood.ms.entity.ProfileJob;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ListResponse {

    public static Map<String, Object> responseGeneric(GenericResponse generic){
        Map<String, Object> response = new HashMap<>();
        response.put("response", generic);
        return response;
    }

    public static Map<String, Object> responseTemplate(TemplateResponse template){
        Map<String, Object> response = new HashMap<>();
        response.put("response", template);
        return  response;
    }

    public static Map<String, Object> responsProfile(ProfileJobResponse profile){
        Map<String, Object> response = new HashMap<>();
        response.put("response", profile);
        return  response;
    }

    public static Map<String, Object> responsRole(RoleResponse rol){
        Map<String, Object> response = new HashMap<>();
        response.put("response", rol);
        return  response;
    }

    public static Map<String, Object> responsTemplate(TemplateResponse template){
        Map<String, Object> response = new HashMap<>();
        response.put("response", template);
        return  response;
    }

}
