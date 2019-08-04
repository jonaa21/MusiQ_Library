package sr.unasat.musiQ_library.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class SongDTO {
    private Long id;
    @NotNull
    private String title;
    @Max(4)
    private int releaseYear;
    private AlbumDTO album;
    private boolean isFavorite;

    public SongDTO() {
    }

    public SongDTO(String title, int releaseYear, AlbumDTO album, boolean isFavorite) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.album = album;
        this.isFavorite = isFavorite;
    }

    public SongDTO(String title, AlbumDTO album) {
        this.title = title;
        this.album = album;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

//    public ArtistDTO getArtist() {
//        if (album == null) {
//            return null;
//        }
//        return album.getArtist();
//    }
//
//    public void setArtist(ArtistDTO artist) {
//        this.album.setArtist(artist);
//    }

    public AlbumDTO getAlbum() {
        if (album == null) {
            return null;
        }
        return album;
    }

    public void setAlbum(AlbumDTO album) {
        this.album = album;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
