package sr.unasat.musiQ_library.states;

import sr.unasat.musiQ_library.entity.Album;

public class AlbumContext {
    private State state;

    public AlbumContext() {
    }

    public void setDecade(Album album){
        if(album.getReleaseYear() >= 1980 && album.getReleaseYear() < 1990){
            this.state = new EightiesState();
        }
        if(album.getReleaseYear() >= 1990 && album.getReleaseYear() < 2000){
            this.state = new NinetiesState();
        }
    }

    public String getDecade(Album album){
        return state.getDecade(album);
    }
}
