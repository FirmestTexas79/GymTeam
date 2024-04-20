package com.gymos.web.mapper;

import com.gymos.web.dto.CommentDto;
import com.gymos.web.models.Comment;

public class CommentMapper {

    public static Comment mapToComment(CommentDto comment){
        Comment commentDto = Comment.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .commentedBy(comment.getCommentedBy())
                .createdOn(comment.getCreatedOn())
                .build();
        return commentDto;
    }

    public static CommentDto mapToCommentDto(Comment comment){
        CommentDto commentDto = CommentDto.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .commentedBy(comment.getCommentedBy())
                .createdOn(comment.getCreatedOn())
                .build();
        return commentDto;
    }
}
