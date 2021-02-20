package powerticket.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import powerticket.backend.domain.Comment;
import powerticket.backend.form.CommentForm;
import powerticket.backend.repository.CommentRepository;
import powerticket.backend.service.CommentService;

import java.util.List;

@Controller
@CrossOrigin({"*"})
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    @ResponseBody
    public long createComment (CommentForm form) {
        Comment comment = new Comment();
        comment.setUsername(form.getUsername());
        comment.setContent(form.getContent());
        return commentService.create(comment);
    }

    @GetMapping("/comment")
    @ResponseBody
    public List<Comment> test () {
        return this.commentService.readAll();
    }
}
