package com.gymos.web.service.impl;

import com.gymos.web.dto.CommentDto;
import com.gymos.web.models.Comment;
import com.gymos.web.models.UserEntity;
import com.gymos.web.repository.CommentRepository;
import com.gymos.web.repository.UserRepository;
import com.gymos.web.security.SecurityUtil;
import com.gymos.web.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.gymos.web.mapper.CommentMapper.mapToComment;
import static com.gymos.web.mapper.CommentMapper.mapToCommentDto;


@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    private UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository){
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<CommentDto> findAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map((comment) -> mapToCommentDto(comment)).collect(Collectors.toList());
    }


    public Comment saveComment(CommentDto commentDto){
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Comment comment = mapToComment(commentDto);
        comment.setCommentedBy(user);
        return commentRepository.save(comment);
    }
}
