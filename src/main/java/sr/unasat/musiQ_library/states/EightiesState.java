package sr.unasat.musiQ_library.states;

import sr.unasat.musiQ_library.entity.Album;

public class EightiesState implements State{
    @Override
    public String getDecade(Album album) {
        return "This album belongs to the 80s";
    }
}
