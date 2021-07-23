package com.dlsys.sifood.ms.dao;

import com.dlsys.sifood.ms.entity.ProfileJob;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProfileJobDao extends CrudRepository<ProfileJob, Long>, JpaSpecificationExecutor<ProfileJob> {
    public List<ProfileJob> findByName(String name);
}
