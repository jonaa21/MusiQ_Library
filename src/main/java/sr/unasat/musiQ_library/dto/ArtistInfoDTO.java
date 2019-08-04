package sr.unasat.musiQ_library.dto;

import javax.validation.constraints.Max;

public class ArtistInfoDTO {

    private Long id;
    @Max(1500)
    private String info;

    public ArtistInfoDTO() {
    }

    public ArtistInfoDTO(String info) {
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
