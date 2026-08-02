package com.abhishek.architectai.repository;

import com.abhishek.architectai.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InterviewRepository extends JpaRepository<Interview,Long> {
    Page<Interview> findByQuestionContainingIgnoreCase(

            String keyword,

            Pageable pageable

    );
    Page<Interview> findByQuestionContainingIgnoreCaseAndScoreGreaterThanEqual(

            String keyword,

            int minScore,

            Pageable pageable

    );
}
