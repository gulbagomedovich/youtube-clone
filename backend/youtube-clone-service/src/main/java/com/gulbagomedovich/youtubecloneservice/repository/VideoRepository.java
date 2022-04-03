package com.gulbagomedovich.youtubecloneservice.repository;

import com.gulbagomedovich.youtubecloneservice.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface VideoRepository extends MongoRepository<Video, String> {
    Video getById(String id);

    List<Video> findAllByUserIdIn(Set<String> subscriptions);
}
