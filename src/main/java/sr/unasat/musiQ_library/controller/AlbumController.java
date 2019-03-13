package sr.unasat.musiQ_library.controller;

import org.modelmapper.ModelMapper;
import sr.unasat.musiQ_library.config.JPAConfiguration;
import sr.unasat.musiQ_library.dto.AlbumDTO;
import sr.unasat.musiQ_library.dto.SongDTO;
import sr.unasat.musiQ_library.entity.Album;
import sr.unasat.musiQ_library.entity.Song;
import sr.unasat.musiQ_library.service.AlbumService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("album")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlbumController {
    private AlbumService albumService = new AlbumService(JPAConfiguration.getEntityManager());
    private ModelMapper modelMapper = new ModelMapper();

    @Path("/list")
    @GET
    public Response findAll() {
        List<AlbumDTO> albumDTOS = new ArrayList<>();
        AlbumDTO albumDTO;
        List<Album> albums = albumService.findAll();
        for (Album album : albums) {
            albumDTO = modelMapper.map(album, AlbumDTO.class);
            albumDTOS.add(albumDTO);
        }
        return Response.ok(albumDTOS).build();
    }

    @Path("/add")
    @POST
    public Response add(@Valid AlbumDTO albumDTO) {
        try {
            Album album = modelMapper.map(albumDTO, Album.class);
            albumService.add(album);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    @Path("/{albumId}")
    @PUT
    public Response update(@PathParam("albumId") Long id, @Valid AlbumDTO albumDTO) {
        try {
            albumDTO.setId(id);
            Album album = modelMapper.map(albumDTO, Album.class);
            albumService.update(album);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    @Path("/{albumId}")
    @DELETE
    public Response remove(@PathParam("albumId") Long id) {
        try {
            albumService.delete(id);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    @Path("/{albumId}")
    @GET
    public Response getAlbum(@PathParam("albumId") Long id) {
        AlbumDTO albumDTO;
        try {
            albumDTO = modelMapper.map(albumService.getAlbum(id), AlbumDTO.class);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(albumDTO).build();
    }

    @Path("/{albumId}/songs")
    @GET
    public List<String> getSongsFromAlbum(@PathParam("albumId") Long albumId) {
        AlbumDTO albumDTO = modelMapper.map(albumService.getAlbum(albumId), AlbumDTO.class);
        List<String> titles = new ArrayList<>();
        for (SongDTO song : albumDTO.getSongList()) {
            titles.add(song.getTitle());
        }
        return titles;
    }

    @Path("/add/{albumId}/songs")
    @POST
    public void addSongsToAlbum(@PathParam("albumId") Long albumId, @Valid List<SongDTO> songDTOs) {
        Album album = albumService.getAlbum(albumId);
        List<Song> albumSongs = album.getSongList();
        for (SongDTO s : songDTOs) {
            Song song = modelMapper.map(s, Song.class);
            albumSongs.add(song);
        }
        albumService.addSongsToAlbum(album, albumSongs);
    }
}
