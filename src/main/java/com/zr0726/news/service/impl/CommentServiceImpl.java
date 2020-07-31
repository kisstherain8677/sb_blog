package com.zr0726.news.service.impl;

import com.zr0726.news.dao.CommentRepository;
import com.zr0726.news.po.Comment;
import com.zr0726.news.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByNewId(Long newId) {
        Sort sort=Sort.by("createTime");
        List<Comment> comments=commentRepository.findByNewsIdAndParentCommentNull(newId,sort);

        return eachComment(comments);
    }

    private List<Comment> eachComment(List<Comment> comments){
        List<Comment> commentView=new ArrayList<>();
        for(Comment comment:comments){
            Comment c=new Comment();
            BeanUtils.copyProperties(comment,c);
            commentView.add(c);
        }
        //合并评论的各层子代到第一级子代集合
        combineChildren(commentView);
        return commentView;
    }

    private void combineChildren(List<Comment> comments){
        for(Comment comment:comments){
            List<Comment> replys1=comment.getReplyComments();
            for(Comment reply1:replys1){
                //循环迭代，找出子代,存放在tempReplys里
                recursively(reply1);
            }
            comment.setReplyComments(tempReplys);
            //清除temp
            tempReplys=new ArrayList<>();
        }
    }

    private List<Comment> tempReplys=new ArrayList<>();
    private void recursively(Comment comment){
        tempReplys.add(comment);//顶节点
        if(comment.getReplyComments().size()>0){
            List<Comment> replys =comment.getReplyComments();
            for(Comment reply:replys){
                tempReplys.add(reply);
                if(reply.getReplyComments().size()>0){
                    recursively(reply);
                }
            }
        }
    }
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId=comment.getParentComment().getId();
        if(parentCommentId!=-1){
            comment.setParentComment(commentRepository.findById(parentCommentId).orElse(null));
        }else{
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);

    }

}
