package sr.unasat.musiQ_library.dao;

import sr.unasat.musiQ_library.entity.Artist;
import sr.unasat.musiQ_library.entity.ArtistTypeCode;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static sr.unasat.musiQ_library.utlis.Constants.ENTITY_EXISTS_MSG;

public class ArtistDAO {

    private EntityManager entityManager;
    private List<Artist> artists;

    public ArtistDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
        artists = findAllArtistsByAsc();
    }

    public List<Artist> findAllArtistsByAsc() {
        entityManager.getTransaction().begin();
        String jpql = "select a from Artist a order by a.artistName ASC";
        TypedQuery<Artist> query = entityManager.createQuery(jpql, Artist.class);
        artists = query.getResultList();
        entityManager.getTransaction().commit();
        return artists;
    }

    public Artist addArtist(Artist artist) {
        entityManager.getTransaction().begin();
        for (int i = 0; i < artists.size(); i++) {
            if (artists.get(i).getArtistName().toLowerCase().trim().equals(
                    artist.getArtistName().toLowerCase().trim())) {
                entityManager.getTransaction().rollback();
                throw new EntityExistsException(ENTITY_EXISTS_MSG);
            }
        }
        entityManager.persist(artist);
        entityManager.getTransaction().commit();
        return artist;
    }

    public Artist findArtistById(Long id) {
        entityManager.getTransaction().begin();
        String jpql = "select a from Artist a where a.id = :id";
        TypedQuery<Artist> query = entityManager.createQuery(jpql, Artist.class);
        query.setParameter("id", id);
        Artist foundArtist = query.getSingleResult();
        entityManager.getTransaction().commit();
        return foundArtist;
    }

    public Artist updateArtist(Artist artist) {
        entityManager.getTransaction().begin();
        entityManager.merge(artist);
        entityManager.getTransaction().commit();
        return artist;
    }

    public Artist deleteArtist(Artist artist) {
        entityManager.getTransaction().begin();
        entityManager.remove(artist);
        entityManager.getTransaction().commit();
        return artist;
    }

    public List<ArtistTypeCode> getTypes() {
        entityManager.getTransaction().begin();
        String jpql = "select atc from ArtistTypeCode atc order by atc.type ASC";
        TypedQuery<ArtistTypeCode> query = entityManager.createQuery(jpql, ArtistTypeCode.class);
        List<ArtistTypeCode> types = query.getResultList();
        entityManager.getTransaction().commit();
        return types;
    }
}
