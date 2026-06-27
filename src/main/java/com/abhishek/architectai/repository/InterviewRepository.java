package com.abhishek.architectai.repository;

import com.abhishek.architectai.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepository extends JpaRepository<Interview,Long> {
}
