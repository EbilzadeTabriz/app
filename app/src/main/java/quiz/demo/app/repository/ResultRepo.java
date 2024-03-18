package quiz.demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quiz.demo.app.entity.Result;
@Repository
public interface ResultRepo extends JpaRepository<Result,Integer> {
}
