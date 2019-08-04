package sr.unasat.musiQ_library.service;

import sr.unasat.musiQ_library.dao.AlbumDAO;
import sr.unasat.musiQ_library.dao.ArtistDAO;
import sr.unasat.musiQ_library.dao.SongDAO;
import sr.unasat.musiQ_library.entity.Album;
import sr.unasat.musiQ_library.entity.Song;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SongService {

    private SongDAO songDAO;
    private AlbumDAO albumDAO;
    private ArtistDAO artistDAO;
    private List<Song> songList;

    public SongService(EntityManager entityManager) {
        songDAO = new SongDAO(entityManager);
        albumDAO = new AlbumDAO(entityManager);
        artistDAO = new ArtistDAO(entityManager);
        songList = findAll();
    }

    public List<Song> findAll() {
        return songDAO.findAllSongsByAsc();
    }

    public Song add(Song song) {
        if (song.getAlbum() != null) {
            if (song.getAlbum().getArtist() != null) {
                List<Album> albums = new ArrayList<>();
                albums.add(song.getAlbum());
                song.getAlbum().getArtist().setAlbum(albums);
                artistDAO.addArtist(song.getAlbum().getArtist());
            }
            albumDAO.addAlbum(song.getAlbum());
        }
        songList.add(songDAO.addSong(song));
        return song;
    }

    public Song getSong(Long id) {
        return songDAO.findSongById(id);
    }

    public Song update(Song song) {
        Song updatedSong = songDAO.updateSong(song);
        iterate(song, updatedSong);
        songList.add(updatedSong);
        return updatedSong;
    }

    public Song delete(Long id) {
        Song selectedSong = getSong(id);
        Song deletedSong = songDAO.deleteSong(selectedSong);
        iterate(selectedSong, deletedSong);
        return deletedSong;
    }

    private void iterate(Song song, Song modifiedSong) {
        for (Iterator<Song> songIterator = songList.iterator(); songIterator.hasNext(); ) {
            song = songIterator.next();
            if (song.getId() == modifiedSong.getId()) {
                songIterator.remove();
            }
        }
    }

}

