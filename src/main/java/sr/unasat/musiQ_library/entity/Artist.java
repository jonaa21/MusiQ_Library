package sr.unasat.musiQ_library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.ws.rs.DefaultValue;
import java.util.List;

@Entity
@Table
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "artist_name", nullable = false, unique = true)
    private String artistName;

    @JsonBackReference(value = "albumArtist")
    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Album> album;

    @Column(name = "is_followed")
    @DefaultValue(value = "false")
    private boolean isFollowed;

    @OneToOne(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArtistInfo artistInfo;

    @Column(name = "artist_type", nullable = false)
    private String artistType;

    public Artist() {
    }

    public Artist(String artistName, String artistType) {
        this.artistName = artistName;
        this.artistType = artistType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String name) {
        this.artistName = name;
    }

    public String getArtistType() {
        return artistType;
    }

    public void setArtistType(String artistType) {
        this.artistType = artistType;
    }

    public List<Album> getAlbum() {
        return album;
    }

    public void setAlbum(List<Album> album) {
        this.album = album;
    }

    public boolean isFollowed() {
        return isFollowed;
    }

    public void setFollowed(boolean followed) {
        isFollowed = followed;
    }

    public ArtistInfo getArtistInfo() {
        return artistInfo;
    }

    public void setArtistInfo(ArtistInfo artistInfo) {
        this.artistInfo = artistInfo;
    }
}
