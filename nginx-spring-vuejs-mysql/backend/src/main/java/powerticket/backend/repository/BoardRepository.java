package powerticket.backend.repository;

import powerticket.backend.domain.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Board save(Board board);
    Optional<Board> findById(long id);
    List<Board> findAll();
}
