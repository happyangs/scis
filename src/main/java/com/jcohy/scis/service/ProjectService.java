package com.jcohy.scis.service;

import com.jcohy.scis.model.Project;

import java.util.List;

public interface ProjectService {

    /**
     *  查询
     * @return
     */
    List<Project> findAll();
}
