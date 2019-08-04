package sr.unasat.musiQ_library.service;

import sr.unasat.musiQ_library.dao.AlbumDAO;
import sr.unasat.musiQ_library.entity.Album;
import sr.unasat.musiQ_library.entity.Song;

import javax.persistence.EntityManager;
import java.util.Iterator;
import java.util.List;

public class AlbumService {

    private AlbumDAO albumDAO;
    private ArtistService artistService;
    private SongService songService;
    private List<Album> albums;

    public AlbumService(EntityManager entityManager) {
        albumDAO = new AlbumDAO(entityManager);
        artistService = new ArtistService(entityManager);
        songService = new SongService(entityManager);
        albums = findAll();
    }

    public List<Album> findAll() {
        return albumDAO.findAllAlbumsByAsc();
    }

    public Album add(Album album) {
        artistService.add(album.getArtist());
        albumDAO.addAlbum(album);
        albums.add(album);
        return album;
    }

    public Album getAlbum(Long id) {
        return albumDAO.findAlbumById(id);
    }

    public Album update(Album album) {
        Album updatedAlbum = albumDAO.updateAlbum(album);
        iterate(album, updatedAlbum);
        albums.add(updatedAlbum);
        return updatedAlbum;
    }

    public Album delete(Long id) {
        Album selectedAlbum = getAlbum(id);
        for (Song song : selectedAlbum.getSongList()) {
            song.setAlbum(null);
        }
        selectedAlbum.setSongList(null);
        Album deletedAlbum = albumDAO.deleteAlbum(selectedAlbum);
        iterate(selectedAlbum, deletedAlbum);
        return deletedAlbum;
    }

    private void iterate(Album album, Album modifiedAlbum) {
        for (Iterator<Album> albumIterator = albums.iterator(); albumIterator.hasNext(); ) {
            album = albumIterator.next();
            if (album.getId() == modifiedAlbum.getId()) {
                albumIterator.remove();
            }
        }
    }

    public void addSongsToAlbum(Album album, List<Song> songs) {
        album.setSongList(songs);
        for (Song s : songs) {
            s.setAlbum(album);
            songService.add(s);
        }
        update(album);
    }


}

