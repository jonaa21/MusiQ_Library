package sr.unasat.musiQ_library.designPatterns.builder;

public interface Builder {

    void setSongTitle(String songTitle);

    void setArtist(String artist);

    void setArtistType(String artistType);

    void setAlbum(String album);

    void setReleaseYear(int year);
}
