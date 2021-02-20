package powerticket.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import powerticket.backend.repository.BoardRepository;
import powerticket.backend.repository.CommentRepository;
import powerticket.backend.repository.JpaBoardRepository;
import powerticket.backend.repository.JpaCommentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class JpaConfig {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public JpaConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public BoardRepository boardRepository() {
        return new JpaBoardRepository(em);
    }

    @Bean
    public CommentRepository commentRepository() {
        return new JpaCommentRepository(em);
    }
}
