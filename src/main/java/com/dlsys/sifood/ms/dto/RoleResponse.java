package com.dlsys.sifood.ms.dto;

import com.dlsys.sifood.ms.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleResponse extends GenericResponse {

    private Role role;
    private List<Role> roles;

    public RoleResponse(String statusCode, String statusResponse, List<String> description, Role role) {
        super(statusCode, statusResponse, description);
        this.role = role;
    }

    public RoleResponse(String statusCode, String statusResponse, List<String> description, List<Role>  roles) {
        super(statusCode, statusResponse, description);
        this.roles = roles;
    }
}
