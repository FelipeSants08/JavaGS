package br.com.fiap.resource;

import br.com.fiap.bo.OrcamentoBO;
import br.com.fiap.to.OrcamentoOffGridTO;
import br.com.fiap.to.OrcamentoOnGridTO;

import br.com.fiap.to.OrcamentoTO;
import br.com.fiap.to.PlacaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/orcamentos")
public class OrcamentoResource {

    private OrcamentoBO orcamentoBO = new OrcamentoBO();
    private PlacaTO placaBO;

    // Endpoint para criar um orçamento On-Grid
    @POST
    @Path("/on-grid")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOnGrid(OrcamentoOnGridTO orcamento, @QueryParam("potenciaPlaca") Double potenciaPlaca) {
        if (potenciaPlaca == null || potenciaPlaca <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("A potência da placa deve ser informada e maior que zero.")
                    .build();
        }
        orcamento.calcularQuantidadePlacas(potenciaPlaca);
        orcamento.calcularCustoTotal();
        boolean isSaved = orcamentoBO.saveOrcamento(orcamento);
        if (isSaved) {
            return Response.status(Response.Status.CREATED).entity(orcamento).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    // Endpoint para criar um orçamento Off-Grid
    @POST
    @Path("/off-grid")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOffGrid(OrcamentoOffGridTO orcamento, @QueryParam("potenciaPlaca") Double potenciaPlaca) {
        if (potenciaPlaca == null || potenciaPlaca <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("A potência da placa deve ser informada e maior que zero.")
                    .build();
        }
        orcamento.calcularQuantidadePlacas(potenciaPlaca); // Cálculo com a potência informada
        orcamento.calcularCustoTotal(); // Calcula o custo total
        boolean isSaved = orcamentoBO.saveOrcamento(orcamento);
        if (isSaved) {
            return Response.status(Response.Status.CREATED).entity(orcamento).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    // Endpoint para listar todos os orçamentos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrcamentos() {
        return Response.ok(orcamentoBO.findAllOrcamentos()).build();
    }

    // Endpoint para buscar um orçamento por ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrcamentoById(@PathParam("id") Long id) {
        OrcamentoTO orcamento = orcamentoBO.findOrcamentoById(id); // Substitui 'var' pelo tipo explícito
        if (orcamento != null) {
            return Response.ok(orcamento).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // Endpoint para deletar um orçamento
    @DELETE
    @Path("/{id}")
    public Response deleteOrcamento(@PathParam("id") Long id) {
        boolean isDeleted = orcamentoBO.deleteOrcamento(id);
        if (isDeleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // Endpoint para atualizar um orçamento On-Grid
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrcamento(@PathParam("id") Long id, @Valid OrcamentoTO orcamentoAtualizado) {
        // Define o ID no orçamento atualizado
        orcamentoAtualizado.setCodigo(id);

        // Obtém a potência da placa dinamicamente
        Double potenciaPlaca = placaBO.getPotenciaPlaca(); // Supondo que "placaBO" seja o responsável por gerenciar placas
        if (potenciaPlaca == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Potência da placa não encontrada").build();
        }

        // Recalcula os valores do orçamento
        orcamentoAtualizado.calcularQuantidadePlacas(potenciaPlaca);
        orcamentoAtualizado.calcularCustoTotal();

        // Chama o BO para realizar a atualização
        boolean atualizado = orcamentoBO.updateOrcamento(orcamentoAtualizado);

        if (atualizado) {
            return Response.ok(orcamentoAtualizado).build(); // Retorna o orçamento atualizado
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Orçamento não encontrado").build();
        }
    }

}
