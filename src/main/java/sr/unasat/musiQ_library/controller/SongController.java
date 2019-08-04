package sr.unasat.musiQ_library.controller;

import org.modelmapper.ModelMapper;
import sr.unasat.musiQ_library.config.JPAConfiguration;
import sr.unasat.musiQ_library.designPatterns.builder.BuilderImpl;
import sr.unasat.musiQ_library.dto.ArtistDTO;
import sr.unasat.musiQ_library.dto.SongDTO;
import sr.unasat.musiQ_library.entity.Song;
import sr.unasat.musiQ_library.service.SongService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Path("song")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SongController {
    private SongService songService = new SongService(JPAConfiguration.getEntityManager());
    private ModelMapper modelMapper = new ModelMapper();

    @Path("/list")
    @GET
    public Response findAll() {
        List<SongDTO> songDTOS = new ArrayList<>();
        List<Song> songs = songService.findAll();
        SongDTO songDTO;
        for (Song song : songs) {
            ArtistDTO artistDTO = modelMapper.map(song.getAlbum().getArtist(), ArtistDTO.class);
            songDTO = modelMapper.map(song, SongDTO.class);
            songDTO.getAlbum().setArtist(artistDTO);
            songDTOS.add(songDTO);
        }
        return Response.ok(songDTOS).build();
    }

    @Path("/add")
    @POST
    public Response add(@Valid SongDTO songDTO) {
        try {
//            if (songDTO.getAlbum() != null) {
//                ArtistTypeCode artistTypeCode = modelMapper.map(songDTO.getAlbum().getArtist().getArtistType(), ArtistTypeCode.class);
//                Artist artist = modelMapper.map(songDTO.getAlbum().getArtist(), Artist.class);
//                artist.setArtistType(artistTypeCode);
//            }
//            Song song = modelMapper.map(songDTO, Song.class);
            BuilderImpl builder = new BuilderImpl();
            builder.setSongTitle(songDTO.getTitle());
            builder.setAlbum(songDTO.getAlbum().getAlbumTitle());
            builder.setArtist(songDTO.getAlbum().getArtist().getArtistName());
            builder.setArtistType(songDTO.getAlbum().getArtist().getArtistType());
            builder.setReleaseYear(songDTO.getReleaseYear());
            songService.add(builder.build());
        } catch (Exception e) {
            JPAConfiguration.getEntityManager().getTransaction().rollback();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @Path("/{songId}")
    @PUT
    public Response update(@PathParam("songId") Long id, @Valid SongDTO songDTO) {
        try {
            songDTO.setId(id);
            Song song = modelMapper.map(songDTO, Song.class);
            songService.update(song);
        } catch (Exception e) {
            JPAConfiguration.getEntityManager().getTransaction().rollback();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @Path("/{songId}")
    @DELETE
    public Response remove(@PathParam("songId") Long id) {
        try {
            songService.delete(id);
        } catch (Exception e) {
            JPAConfiguration.getEntityManager().getTransaction().rollback();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @Path("/{songId}")
    @GET
    public Response getSong(@PathParam("songId") Long id) {
        SongDTO songDTO;
        try {
            Song song = songService.getSong(id);
            songDTO = modelMapper.map(song, SongDTO.class);
        } catch (Exception e) {
            JPAConfiguration.getEntityManager().getTransaction().rollback();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(songDTO).build();
    }

    @Path("/random")
    @GET
    public Response getRandomSongs() {
        List<Song> songs = songService.findAll();
        List<SongDTO> randomSongs = new ArrayList<>();
        Random random = new Random();
        SongDTO songDTO;
        Song s;
        do {
            s = songs.get(random.nextInt(songs.size()));
            for (SongDTO dto : randomSongs) {
                if (dto.getId() == s.getId()) {
                    s = songs.get(random.nextInt(songs.size()));
                }
            }
            ArtistDTO artistDTO = modelMapper.map(s.getAlbum().getArtist(), ArtistDTO.class);
            songDTO = modelMapper.map(s, SongDTO.class);
            songDTO.getAlbum().setArtist(artistDTO);
            randomSongs.add(songDTO);
        } while (randomSongs.size() < 5);
        return Response.ok(randomSongs).build();
    }
}
