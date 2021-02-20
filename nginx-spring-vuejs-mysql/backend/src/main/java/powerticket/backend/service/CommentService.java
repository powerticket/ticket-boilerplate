package powerticket.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import powerticket.backend.domain.Comment;
import powerticket.backend.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * 댓글 작성
     * @param comment
     * @return comment id
     */
    public long create(Comment comment) {
        commentRepository.save(comment);
        return comment.getId();
    }

    /**
     * 댓글 조회
     * @return comment list
     */
    public List<Comment> readAll() {
        return this.commentRepository.findAll();
    }
}
