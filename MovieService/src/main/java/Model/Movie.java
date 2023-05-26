package Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private MovieCategory category;
    private String description;
    @NotNull
    private Boolean isAvailable;

    public Movie(Long id, String name, MovieCategory category) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = "";
        this.isAvailable = false;
    }

}
