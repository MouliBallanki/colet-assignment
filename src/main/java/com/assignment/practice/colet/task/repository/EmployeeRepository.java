package com.assignment.practice.colet.task.repository;

import com.assignment.practice.colet.task.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("SELECT DISTINCT e FROM Employee e " +
            "LEFT JOIN e.performanceReviews pr " +
            "LEFT JOIN e.department d " +
            "LEFT JOIN EmployeeProject ep ON ep.employee = e " +
            "LEFT JOIN ep.project p " +
            "WHERE (:reviewDate IS NULL OR pr.reviewDate = :reviewDate) " +
            "AND (:score IS NULL OR pr.score = :score) " +
            "AND (:departmentIds IS NULL OR d.id IN :departmentIds) " +
            "AND (:projectNames IS NULL OR p.name IN :projectNames)")
    List<Employee> findEmployeesWithFilters(
            @Param("reviewDate") LocalDate reviewDate,
            @Param("score") String score,
            @Param("departmentIds") List<Long> departmentIds,
            @Param("projectNames") List<String> projectNames
    );
}
