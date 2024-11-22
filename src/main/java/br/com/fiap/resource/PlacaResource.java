package br.com.fiap.resource;

import br.com.fiap.bo.PlacaBO;
import br.com.fiap.to.PlacaTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/placas")
public class PlacaResource {

    private PlacaBO placaBO = new PlacaBO();

    // Endpoint para listar todas as placas
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllPlacas() {
        ArrayList<PlacaTO> placas = placaBO.findAllPlacas();
        if (placas != null && !placas.isEmpty()) {
            return Response.ok(placas).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Nenhuma placa encontrada.").build();
    }

    // Endpoint para buscar uma placa por código
    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPlacaByCodigo(@PathParam("codigo") Long codigo) {
        PlacaTO placa = placaBO.findById(codigo);
        if (placa != null) {
            return Response.ok(placa).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Placa não encontrada com o código: " + codigo).build();
    }

    // Endpoint para criar uma nova placa
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response savePlaca(@Valid PlacaTO placa) {
        PlacaTO novaPlaca = placaBO.savePlaca(placa);
        if (novaPlaca != null) {
            return Response.status(Response.Status.CREATED).entity(novaPlaca).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao salvar a placa. Verifique os dados.").build();
    }

    // Endpoint para atualizar uma placa
    @PUT
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePlaca(@PathParam("codigo") Long codigo, @Valid PlacaTO placaAtualizada) {
        placaAtualizada.setCodigo(codigo); // Define o código para atualizar
        PlacaTO placa = placaBO.updatePlaca(placaAtualizada);
        if (placa != null) {
            return Response.ok(placa).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Placa não encontrada para o código: " + codigo).build();
    }

    // Endpoint para deletar uma placa
    @DELETE
    @Path("/{codigo}")
    public Response deletePlaca(@PathParam("codigo") Long codigo) {
        boolean excluida = placaBO.deletePlaca(codigo);
        if (excluida) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Placa não encontrada para o código: " + codigo).build();
    }
}
