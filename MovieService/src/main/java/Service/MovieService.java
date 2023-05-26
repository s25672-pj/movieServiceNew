package Service;

import Exceptions.*;
import Model.Movie;
import Repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovies(){
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id){
        return movieRepository.findById(id).orElseThrow(MovieNotFoundException::new);
    }

    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public void updateMovie(Movie movieWithUpdatedData){
        Movie movieInBase = getMovieById(movieWithUpdatedData.getId());
        if(movieInBase != null){
            if(movieWithUpdatedData.getName() != null){
                movieInBase.setName(movieWithUpdatedData.getName());
            }
            if(movieWithUpdatedData.getCategory() != null){
                movieInBase.setCategory(movieWithUpdatedData.getCategory());
            }
            if(movieWithUpdatedData.getDescription() != null){
                movieInBase.setDescription(movieWithUpdatedData.getDescription());
            }
        }else {
            throw new IllegalArgumentException("Nie udało sie zaktualizować, film o id " + movieWithUpdatedData.getId() + " nie został odnaleziony w bazie");
        }
        movieRepository.save(movieInBase);
    }

    public void deleteMovie(Long id){
        Movie movieToDelete = getMovieById(id);
        if (movieToDelete != null){
          movieRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException("Nie udało się usunąć, film  id = " + id + ", nie został odnaleziony w bazie");
        }
    }

    public void changeIsA(Long id) {
       Movie target = getMovieById(id);
       target.setIsAvailable(!target.getIsAvailable());
       movieRepository.save(target);
       }
    }

