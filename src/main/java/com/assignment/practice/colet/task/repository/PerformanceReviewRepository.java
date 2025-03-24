package com.assignment.practice.colet.task.repository;

import com.assignment.practice.colet.task.entity.PerformanceReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceReviewRepository extends JpaRepository<PerformanceReview,Long> {
}
