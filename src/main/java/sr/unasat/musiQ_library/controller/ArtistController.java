package sr.unasat.musiQ_library.controller;

import org.modelmapper.ModelMapper;
import sr.unasat.musiQ_library.config.JPAConfiguration;
import sr.unasat.musiQ_library.dto.ArtistDTO;
import sr.unasat.musiQ_library.entity.Artist;
import sr.unasat.musiQ_library.entity.ArtistInfo;
import sr.unasat.musiQ_library.entity.ArtistTypeCode;
import sr.unasat.musiQ_library.service.ArtistService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("artist")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArtistController {
    private ArtistService artistService = new ArtistService(JPAConfiguration.getEntityManager());
    private ModelMapper modelMapper = new ModelMapper();

    @Path("/list")
    @GET
    public Response findAll() {
        List<ArtistDTO> artistDTOS = new ArrayList<>();
        ArtistDTO artistDTO;
        List<Artist> artists = artistService.findAll();
        for (Artist a : artists) {
            artistDTO = modelMapper.map(a, ArtistDTO.class);
            artistDTOS.add(artistDTO);
        }
        return Response.ok(artistDTOS).build();
    }

    @Path("/add")
    @POST
    public Response add(ArtistDTO artistDTO) {
        try {
            Artist artist = modelMapper.map(artistDTO, Artist.class);
            artistService.add(artist);
        } catch (Exception e) {
            JPAConfiguration.getEntityManager().getTransaction().rollback();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    @Path("/update/{artistId}")
    @PUT
    public Response update(@PathParam("artistId") Long id, @Valid String info) {
        try {
            Artist artist = artistService.getArtist(id);
            artist.setArtistInfo(new ArtistInfo(artist, info));
            artistService.update(artist);
        } catch (Exception e) {
            JPAConfiguration.getEntityManager().getTransaction().rollback();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    @Path("/{artistId}")
    @DELETE
    public Response remove(@PathParam("artistId") Long id) {
        try {
            artistService.delete(id);
        } catch (Exception e) {
//            JPAConfiguration.getEntityManager().getTransaction().rollback();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    @Path("/{artistId}")
    @GET
    public Response getArtist(@PathParam("artistId") Long id) {
        ArtistDTO artistDTO;
        try {
            artistDTO = modelMapper.map(artistService.getArtist(id), ArtistDTO.class);
        } catch (Exception e) {
            JPAConfiguration.getEntityManager().getTransaction().rollback();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(artistDTO).build();
    }


    @Path("/types")
    @GET
    public Response getTypes() {
        List<ArtistTypeCode> types = artistService.getTypes();
        return Response.ok(types).build();
    }
}
