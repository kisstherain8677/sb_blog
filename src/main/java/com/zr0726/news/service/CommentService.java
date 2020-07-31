package com.zr0726.news.service;

import com.zr0726.news.po.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> listCommentByNewId(Long newId);
    Comment saveComment(Comment comment);
}
