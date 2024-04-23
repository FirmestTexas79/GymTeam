package com.gymos.web.service;

import com.gymos.web.dto.CommentDto;
import com.gymos.web.models.Comment;

import java.util.List;

public interface CommentService {
    //Logika a provádění operace s daty

    List<CommentDto> findAllComments();

    Comment saveComment(CommentDto commentDto);
}
