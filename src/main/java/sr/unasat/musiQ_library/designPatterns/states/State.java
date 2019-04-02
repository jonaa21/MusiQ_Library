package sr.unasat.musiQ_library.designPatterns.states;

import sr.unasat.musiQ_library.entity.Album;

public interface State {
    String getDecade(Album album);
}
