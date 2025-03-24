package com.assignment.practice.colet.task.service.project;

import com.assignment.practice.colet.task.entity.Project;
import com.assignment.practice.colet.task.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }
}
