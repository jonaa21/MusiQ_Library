package sr.unasat.musiQ_library.dto;

import sr.unasat.musiQ_library.designPatterns.decorator.Decorator;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class AlbumDTO extends Decorator {
    private Long id;
    @NotNull
    private String albumTitle;
    @Max(4)
    private int releaseYear;
    @NotNull
    private ArtistDTO artist;
    private Decorator decorator = new Decorator();
    private List<SongDTO> songList = new ArrayList<>();

    public AlbumDTO() {
        super();
    }

    public AlbumDTO(ArtistDTO artistDTO, List<SongDTO> songDTOS) {
        super(artistDTO, songDTOS);
    }

    public AlbumDTO(String albumTitle, ArtistDTO artist, int releaseYear) {
        this.albumTitle = albumTitle;
        this.artist = artist;
        this.releaseYear = releaseYear;
    }

    public AlbumDTO(String albumTitle, int releaseYear) {
        this.albumTitle = albumTitle;
        this.releaseYear = releaseYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public ArtistDTO getArtist() {
        return artist = decorator.getArtist();
//        return artist;
    }

    public void setArtist(ArtistDTO artist) {
        decorator.setArtist(artist);
    }

    public List<String> getSongList() {
        return decorator.getSongList();
    }

    public void setSongList(List<SongDTO> songList) {
        decorator.setSongList(songList);
//        this.songList = songList;
    }
}
