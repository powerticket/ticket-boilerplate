// package powerticket.backend.service;

// import org.assertj.core.api.Assertions;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import powerticket.backend.domain.Comment;

// import javax.transaction.Transactional;
// import java.util.List;

// import static org.assertj.core.util.Lists.list;

// @SpringBootTest
// @Transactional
// class CommentServiceIntegrationTest {

//     @Autowired
//     CommentService commentService;

//     @Test
//     void 댓글_작성_조회() {
//         // given
//         Comment comment1 = new Comment();
//         comment1.setUsername("user1");
//         comment1.setContent("content1");
//         Comment comment2 = new Comment();
//         comment2.setUsername("user2");
//         comment2.setContent("content2");

//         // when
//         commentService.create(comment1);
//         commentService.create(comment2);

//         // then
//         List<Comment> readCommentList = commentService.readAll();
//         Assertions.assertThat(readCommentList).isEqualTo(list(comment1, comment2));
//     }
// }