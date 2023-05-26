package Repository;

import Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Override
    Optional<Movie> findById(Long aLong);

    @Override
    <S extends Movie> S save(S entity);

    @Override
    void deleteById(Long aLong);

    @Override
    List<Movie> findAll();
}
