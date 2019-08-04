package sr.unasat.musiQ_library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.ws.rs.DefaultValue;

@Entity
@Table
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "release_year")
    private int releaseYear;

    @ManyToOne
    @JsonBackReference(value = "albumSong")
    @JoinColumn(name = "album_id")
    private Album album;

    @Column(name = "is_favorite")
    @DefaultValue(value = "false")
    private boolean isFavorite;

    public Song() {

    }

    public Song(Long id, String title, int releaseYear, Album album, boolean isFavorite) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.album = album;
        this.isFavorite = isFavorite;
    }

    public Song(String title) {
        this.title = title;
    }

    public Song(String title, Album album) {
        this.title = title;
        this.releaseYear = album.getReleaseYear();
        this.album = album;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Album getAlbum() {
        return album;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
}
