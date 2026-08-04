package com.abhishek.architectai.specification;

import com.abhishek.architectai.entity.Interview;
import org.springframework.data.jpa.domain.Specification;


public class InterviewSpecification {

    public static Specification<Interview> hasKeyword(String keyword) {

        return (root, query, criteriaBuilder) ->

                keyword == null || keyword.isBlank()

                        ? null

                        : criteriaBuilder.like(

                        criteriaBuilder.lower(
                                root.get("question")
                        ),

                        "%" + keyword.toLowerCase() + "%"

                );
    }
    public static Specification<Interview> hasMinimumScore(
            Integer minScore) {

        return (root, query, criteriaBuilder) ->

                minScore == null

                        ? null

                        : criteriaBuilder.greaterThanOrEqualTo(

                        root.get("score"),

                        minScore

                );
    }
    public static Specification<Interview> hasMaximumScore(
            Integer maxScore) {

        return (root, query, criteriaBuilder) ->

                maxScore == null

                        ? null

                        : criteriaBuilder.lessThanOrEqualTo(

                        root.get("score"),

                        maxScore

                );
    }
}