package sr.unasat.musiQ_library.dao;

import sr.unasat.musiQ_library.entity.Album;
import sr.unasat.musiQ_library.entity.Song;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static sr.unasat.musiQ_library.utlis.Constants.ENTITY_EXISTS_MSG;

public class AlbumDAO {

    private EntityManager entityManager;
    private List<Album> albums;

    public AlbumDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
        albums = findAllAlbumsByAsc();
    }

    public List<Album> findAllAlbumsByAsc() {
        entityManager.getTransaction().begin();
        String jpql = "select a from Album a ORDER BY a.albumTitle ASC";
        TypedQuery<Album> query = entityManager.createQuery(jpql, Album.class);
        albums = query.getResultList();
        entityManager.getTransaction().commit();
        return albums;
    }

    public Album addAlbum(Album album) {
        entityManager.getTransaction().begin();
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).getAlbumTitle().toLowerCase().trim().equals(
                    album.getAlbumTitle().toLowerCase().trim())) {
                entityManager.getTransaction().rollback();
                throw new EntityExistsException(ENTITY_EXISTS_MSG);
            }
        }
        entityManager.persist(album);
        entityManager.getTransaction().commit();
        return album;
    }

    public Album findAlbumById(Long id) {
        entityManager.getTransaction().begin();
        String jpql = "select a from Album a where a.id = :id";
        TypedQuery<Album> query = entityManager.createQuery(jpql, Album.class);
        query.setParameter("id", id);
        Album foundAlbum = query.getSingleResult();
        entityManager.getTransaction().commit();
        return foundAlbum;
    }

    public Album updateAlbum(Album album) {
        entityManager.getTransaction().begin();
        if (album.getSongList() != null) {
            List<Song> songs = album.getSongList();
            for (int i = 0; i < songs.size(); i++) {
                String songTitle = songs.get(i).getTitle();
                songs.add(new Song(songTitle, album));
            }
            album.setSongList(songs);
        }
        entityManager.merge(album);
        entityManager.getTransaction().commit();
        return album;
    }

    public Album deleteAlbum(Album album) {
        entityManager.getTransaction().begin();
        entityManager.remove(album);
        entityManager.getTransaction().commit();
        return album;
    }
}
