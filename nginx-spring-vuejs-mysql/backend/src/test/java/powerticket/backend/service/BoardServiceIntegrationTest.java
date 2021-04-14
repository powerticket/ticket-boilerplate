// package powerticket.backend.service;

// import org.assertj.core.api.Assertions;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.transaction.annotation.Transactional;
// import powerticket.backend.domain.Board;
// import powerticket.backend.repository.BoardRepository;

// import java.util.List;
// import java.util.Optional;

// import static org.assertj.core.api.Assertions.*;
// import static org.assertj.core.util.Lists.list;
// import static org.junit.jupiter.api.Assertions.*;

// @SpringBootTest
// @Transactional
// class BoardServiceIntegrationTest {

//     @Autowired
//     BoardService boardService;

//     @Test
//     void 작성_조회() {
//         // given
//         Board board = new Board();
//         board.setUsername("유저이름1");
//         board.setPassword("비밀번호1");
//         board.setTitle("게시글1 제목입니다.");
//         board.setContent("게시글1 내용입니다.");

//         // when
//         Long saveId = boardService.create(board);

//         // then
//         Optional<Board> readBoard1 = boardService.read(saveId);
//         assertThat(board).isEqualTo(readBoard1.get());
//     }

//     @Test
//     void 전체_조회() {
//         // given
//         Board board1 = new Board();
//         board1.setUsername("유저이름1");
//         board1.setPassword("비밀번호1");
//         board1.setTitle("게시글1 제목입니다.");
//         board1.setContent("게시글1 내용입니다.");

//         Board board2 = new Board();
//         board2.setUsername("유저이름2");
//         board2.setPassword("비밀번호2");
//         board2.setTitle("게시글2 제목입니다.");
//         board2.setContent("게시글2 내용입니다.");

//         // when
//         Long saveId1 = boardService.create(board1);
//         Long saveId2 = boardService.create(board2);

//         // then
//         List<Board> boardList = list(board1, board2);
//         System.out.println("boardList = " + boardList);
//         List<Board> readBoardList = boardService.readAll();
//         System.out.println("readBoardList = " + readBoardList);

//         assertThat(boardList).isEqualTo(readBoardList);
//     }

// }