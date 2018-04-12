package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Recommendation;

public interface RecommendationRepository extends CrudRepository<Recommendation, Long> {

}
