package com.zr0726.news.web;

import com.zr0726.news.po.Comment;
import com.zr0726.news.po.User;
import com.zr0726.news.service.CommentService;
import com.zr0726.news.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private NewService newService;

    private String avatar="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1596010448468&di=907ebbe0a67751f8c93d79da1c9a2c6a&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2F201608%2F04%2F2339412b3z30zbd2j6k652.jpg";

    @GetMapping("/comments/{newId}")
    private String comments(@PathVariable Long newId, Model model){
        model.addAttribute("comments",commentService.listCommentByNewId(newId));
        return "new::commentList";
    }
    @PostMapping("/comments")
    private String post(Comment comment, HttpSession session){
        Long newId=comment.getNews().getId();
        comment.setNews(newService.getNew(newId));
        User user=(User) session.getAttribute("user");
        if(user!=null){//是管理员，因为只有管理员能登录
            comment.setAdminComment(true);
            comment.setAvatar(avatar);
        }else{
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        //System.out.println(commentService.saveComment(comment));
        return "redirect:/comments/"+newId;
    }
}
