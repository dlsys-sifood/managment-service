package com.dlsys.sifood.ms.dao;

import com.dlsys.sifood.ms.entity.Template;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ITemplateDao extends CrudRepository<Template, Long>, JpaSpecificationExecutor<Template> {
    public List<Template> findByName(String name);
    public List<Template> findByUrl(String url);
}
