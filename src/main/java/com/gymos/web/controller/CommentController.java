package com.gymos.web.controller;


import com.gymos.web.dto.CommentDto;
import com.gymos.web.models.Comment;
import com.gymos.web.models.UserEntity;
import com.gymos.web.security.SecurityUtil;
import com.gymos.web.service.CommentService;
import com.gymos.web.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class CommentController {
    private CommentService commentService;
    private UserService userService;

    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }


    @GetMapping("/comments")
    public String listComments(Model model){
        UserEntity user = new UserEntity();
        List<CommentDto> comments = commentService.findAllComments();
        String username = SecurityUtil.getSessionUser();
        if(username != null){
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("comments", comments);
        return "comments-list";
    }

    @GetMapping("/comments/new")
    public String createCommentForm(Model model){
        Comment comment = new Comment();
        model.addAttribute("comment", comment);
        return "comments-create";
    }

    @PostMapping("/comments/new")
    public String saveComment(@Valid @ModelAttribute("comment")CommentDto commentDto, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("comment", commentDto);
            return "comments-create";
        }
        commentService.saveComment(commentDto);
        return "redirect:/comments";
    }
}
