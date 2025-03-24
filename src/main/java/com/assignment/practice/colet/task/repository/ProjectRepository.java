package com.assignment.practice.colet.task.repository;

import com.assignment.practice.colet.task.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findByDepartmentId(Long departmentId);
}
