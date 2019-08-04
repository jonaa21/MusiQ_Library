package sr.unasat.musiQ_library.entity;

import javax.persistence.*;

@Entity
@Table(name = "artist_type_code")
public class ArtistTypeCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "artist_type")
    private String type;

    public ArtistTypeCode() {
    }

    public ArtistTypeCode(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String artistType) {
        this.type = artistType;
    }
}
