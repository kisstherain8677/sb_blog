package com.zr0726.news.dao;

import com.zr0726.news.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByNewsIdAndParentCommentNull(Long newId, Sort sort);
}
