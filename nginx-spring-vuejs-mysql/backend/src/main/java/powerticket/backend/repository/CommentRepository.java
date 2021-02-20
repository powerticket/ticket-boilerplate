package powerticket.backend.repository;

import powerticket.backend.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(long id);
    List<Comment> findAll();
}
