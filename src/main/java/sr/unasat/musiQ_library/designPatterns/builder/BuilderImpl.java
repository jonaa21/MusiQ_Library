package sr.unasat.musiQ_library.designPatterns.builder;

import sr.unasat.musiQ_library.entity.Album;
import sr.unasat.musiQ_library.entity.Artist;
import sr.unasat.musiQ_library.entity.Song;

public class BuilderImpl implements Builder {

    private String songTitle;
    private String artist;
    private String album;
    private String artistType;
    private int releaseYear;

    public Song build() {
        return new Song(songTitle, new Album(album,
                new Artist(artist, artistType), releaseYear));
    }

    @Override
    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    @Override
    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public void setArtistType(String artistType) {
        this.artistType = artistType;
    }

    @Override
    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
