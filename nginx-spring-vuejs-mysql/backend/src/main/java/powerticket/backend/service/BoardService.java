package powerticket.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import powerticket.backend.domain.Board;
import powerticket.backend.repository.BoardRepository;

import java.util.List;
import java.util.Optional;


@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    /**
     * 게시글 작성
     * @param board
     * @return board id
     */
    public long create(Board board) {
        this.boardRepository.save(board);
        return board.getId();
    }

    /**
     * 게시글 상세 조회
     * @param boardId
     * @return board
     */
    public Optional<Board> read(long boardId) {
        return this.boardRepository.findById(boardId);
    }

    /**
     * 게시글 전체 조회
     * @return board list
     */
    public List<Board> readAll() {
        return this.boardRepository.findAll();
    }
}
