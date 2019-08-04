package sr.unasat.musiQ_library.entity;


import javax.persistence.*;

@Entity
@Table(name = "artist_info")
public class ArtistInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    @Column(name = "info", length = 1500)
    private String info;

    public ArtistInfo() {
    }

    public ArtistInfo(Artist artist, String info) {
        this.artist = artist;
        this.info = info;
    }

    public ArtistInfo(String info) {
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String artistInfo) {
        this.info = artistInfo;
    }
}
