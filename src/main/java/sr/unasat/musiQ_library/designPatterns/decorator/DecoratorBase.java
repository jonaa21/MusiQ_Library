package sr.unasat.musiQ_library.designPatterns.decorator;

import sr.unasat.musiQ_library.dto.ArtistDTO;
import sr.unasat.musiQ_library.dto.SongDTO;

import java.util.List;

public interface DecoratorBase {

    ArtistDTO getArtist();

    void setArtist(ArtistDTO artist);

    List<String> getSongList();

    void setSongList(List<SongDTO> songList);

}
