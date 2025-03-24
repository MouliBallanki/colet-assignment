package com.assignment.practice.colet.task.controller;


import com.assignment.practice.colet.task.entity.Project;
import com.assignment.practice.colet.task.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/projects")
    public Project createProject(@RequestBody Project project){
        return projectService.createProject(project);
    }
}
