package com.dlsys.sifood.ms.dto;

import com.dlsys.sifood.ms.entity.ProfileJob;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfileJobResponse extends GenericResponse{

    private ProfileJob profile;
    private List<ProfileJob> profiles;

    public ProfileJobResponse(String statusCode, String statusResponse, List<String> description, ProfileJob profile) {
        super(statusCode, statusResponse, description);
        this.profile = profile;
    }

    public ProfileJobResponse(String statusCode, String statusResponse, List<String> description, List<ProfileJob>  profiles) {
        super(statusCode, statusResponse, description);
        this.profiles = profiles;
    }
}

