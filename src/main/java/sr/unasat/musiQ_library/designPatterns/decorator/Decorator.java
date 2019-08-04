package sr.unasat.musiQ_library.designPatterns.decorator;

import sr.unasat.musiQ_library.dto.ArtistDTO;
import sr.unasat.musiQ_library.dto.SongDTO;

import java.util.ArrayList;
import java.util.List;

public class Decorator implements DecoratorBase {

    private ArtistDTO artistDTO;
    private List<SongDTO> songDTOList = new ArrayList<>();

    public Decorator() {
    }

    public Decorator(ArtistDTO artistDTO, List<SongDTO> songDTOS) {
        this.artistDTO = artistDTO;
        this.songDTOList = songDTOS;
    }

    @Override
    public ArtistDTO getArtist() {
        return artistDTO;
    }

    @Override
    public void setArtist(ArtistDTO artist) {
        this.artistDTO = artist;
    }

    @Override
    public List<String> getSongList() {
        List<String> songs = new ArrayList<>();
        if (songDTOList != null) {
            songDTOList.forEach(song -> songs.add(song.getTitle()));
        }
        return songs;
    }

    @Override
    public void setSongList(List<SongDTO> songList) {
        this.songDTOList = songList;
    }
}
