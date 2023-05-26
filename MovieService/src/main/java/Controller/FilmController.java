package Controller;

import Model.Movie;
import Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class FilmController {

    private final MovieService movieService;

    @Autowired
    public FilmController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllFilms(){
        if(movieService.getMovies() == null){
            return ResponseEntity.ok(movieService.getMovies());
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        if (movieService.getMovieById(id) != null) {
            return ResponseEntity.ok(movieService.getMovieById(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/movies")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        if (movie != null) {
            movieService.addMovie(movie);
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/movies/isAvaiable/{id}")
    public ResponseEntity<Void> updateFilmIsAvailable(@PathVariable Long id){
        movieService.changeIsA(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        if (getMovieById(id) != null) {
            movie.setId(id);
            movieService.updateMovie(movie);
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        if(getMovieById(id) != null){
            movieService.deleteMovie(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
