package sr.unasat.musiQ_library.dao;

import sr.unasat.musiQ_library.entity.Song;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static sr.unasat.musiQ_library.utlis.Constants.ENTITY_EXISTS_MSG;

public class SongDAO {

    private EntityManager entityManager;
    private List<Song> songList;

    public SongDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
        songList = findAllSongsByAsc();
    }

    public List<Song> findAllSongsByAsc() {
        entityManager.getTransaction().begin();
        String jpql = "select s from Song s";
        TypedQuery<Song> query = entityManager.createQuery(jpql, Song.class);
        songList = query.getResultList();
        entityManager.getTransaction().commit();
        return songList;
    }

    public Song addSong(Song song) {
        entityManager.getTransaction().begin();
        for (int i = 0; i < songList.size(); i++) {
            if (songList.get(i).getTitle().toLowerCase().trim().equals(song.getTitle().toLowerCase().trim())) {
                entityManager.getTransaction().rollback();
                throw new EntityExistsException(ENTITY_EXISTS_MSG);
            }
        }
        entityManager.persist(song);
        entityManager.getTransaction().commit();
        return song;
    }

    public Song findSongById(Long id) {
        entityManager.getTransaction().begin();
        String jpql = "select s from Song s where s.id = :id";
        TypedQuery<Song> query = entityManager.createQuery(jpql, Song.class);
        query.setParameter("id", id);
        Song foundSong = query.getSingleResult();
        entityManager.getTransaction().commit();
        return foundSong;
    }

    public Song updateSong(Song song) {
        entityManager.getTransaction().begin();
        entityManager.merge(song);
        entityManager.getTransaction().commit();
        return song;
    }

    public Song deleteSong(Song songToBeDeleted) {
        entityManager.getTransaction().begin();
        entityManager.remove(songToBeDeleted);
        entityManager.getTransaction().commit();
        return songToBeDeleted;
    }
}
