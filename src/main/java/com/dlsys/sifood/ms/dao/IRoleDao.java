package com.dlsys.sifood.ms.dao;

import com.dlsys.sifood.ms.entity.Role;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IRoleDao extends CrudRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    public List<Role> findByName(String name);
}
