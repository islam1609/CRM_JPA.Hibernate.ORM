package JavaPro.JPA.Task.repository;

import JavaPro.JPA.Task.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface courseRepository  extends JpaRepository<Course, Long> {
}
