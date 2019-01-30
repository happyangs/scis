package com.jcohy.scis.service.impl;

import com.jcohy.scis.model.Project;
import com.jcohy.scis.repository.ProjectRepository;
import com.jcohy.scis.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 16:07 2018/4/7
 * Email: jia_chao23@126.com
 * ClassName: ProjectServiceImpl
 * Description:
 **/
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }
}
