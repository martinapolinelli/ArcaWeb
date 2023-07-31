package presentation.rest;

import java.util.ArrayList;
import java.util.List;

import business.AnimaleNonPresenteException;
import business.Arca;
import business.ImbarcaException;
import integration.AnimaleDto;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import presentation.pojo.Animale;

@Path("/animale")
public class ArcaRest {

	@POST
	@Path("/imbarca")
	@Produces(MediaType.APPLICATION_JSON)
	public Response imbarcaAnimale(Animale animale) throws ImbarcaException {
		Arca arca = new Arca();
		AnimaleDto animaleDto = new AnimaleDto();

		if (animale.getId() == null || animale.getSpecie() == null || animale.getPeso() == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		animaleDto.setId(animale.getId());
		animaleDto.setPeso(animale.getPeso());
		animaleDto.setSpecie(animale.getSpecie());

		if (arca.imbarca(animaleDto)) {
			return Response.ok(animaleDto).build();
		}

		return Response.status(Status.BAD_REQUEST).build();
	}

	@DELETE
	@Path("/sbarca")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sbarcaAnimale(Animale animale) throws AnimaleNonPresenteException {
		Arca arca = new Arca();

		if (animale.getId() == null)
			return Response.status(Status.BAD_REQUEST).build();

		if (arca.sbarca(animale.getId())) {
			return Response.ok(animale).build();
		}

		return Response.status(Status.BAD_REQUEST).build();
	}

	@GET
	@Path("/lista")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLista() {
		Arca arca = new Arca();
		List<AnimaleDto> animaliPerPeso = new ArrayList<>();
		animaliPerPeso = arca.getAnimaliPerPeso();

		return Response.ok(animaliPerPeso).build();
	}
	@PUT
	@Path("/newpeso")
	@Produces(MediaType.APPLICATION_JSON)
	public Response modificaPeso(Animale animale) throws AnimaleNonPresenteException {
		Arca arca = new Arca();

		if (animale.getId() == null)
			return Response.status(Status.BAD_REQUEST).build();

		if (arca.modifyPeso(animale.getId(), animale.getPeso())) {
			return Response.ok().build();
		}

		return Response.status(Status.BAD_REQUEST).build();
	}

	@GET
	@Path("/animalesingolo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnimale(Animale animale) throws AnimaleNonPresenteException {
		Arca arca = new Arca();

		if (animale.getId() == null)
			return Response.status(Status.BAD_REQUEST).build();

		arca.getAnimaleSingolo(animale.getId());
		return Response.ok(animale).build();

	}

}
