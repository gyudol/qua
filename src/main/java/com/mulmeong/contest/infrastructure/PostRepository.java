package com.mulmeong.contest.infrastructure;

import com.mulmeong.contest.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
