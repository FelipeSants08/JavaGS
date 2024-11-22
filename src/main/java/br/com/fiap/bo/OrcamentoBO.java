package br.com.fiap.bo;

import br.com.fiap.dao.OrcamentoDAO;
import br.com.fiap.to.OrcamentoOffGridTO;
import br.com.fiap.to.OrcamentoOnGridTO;
import br.com.fiap.to.OrcamentoTO;

import java.util.ArrayList;

public class OrcamentoBO {

    private ArrayList<OrcamentoTO> orcamentos = new ArrayList<>();

    // Salvar orçamento
    public boolean saveOrcamento(OrcamentoTO orcamento) {
        if (orcamento instanceof OrcamentoOnGridTO) {
            calcularOrcamentoOnGrid((OrcamentoOnGridTO) orcamento);
        } else if (orcamento instanceof OrcamentoOffGridTO) {
            calcularOrcamentoOffGrid((OrcamentoOffGridTO) orcamento);
        }
        return orcamentos.add(orcamento);
    }

    public boolean updateOrcamento(OrcamentoTO orcamento) {
        OrcamentoDAO orcamentoDAO = new OrcamentoDAO();

        // Verifica se o orçamento existe antes de atualizar
        OrcamentoTO existente = orcamentoDAO.findById(orcamento.getCodigo());
        if (existente == null) {
            return false; // Orçamento não encontrado
        }

        // Atualiza o orçamento com base nos dados fornecidos
        return orcamentoDAO.update(orcamento);
    }


    // Calcular orçamento On-Grid
    private void calcularOrcamentoOnGrid(OrcamentoOnGridTO orcamento) {
        double potenciaPlaca = 400.0; // Exemplo: potência de cada placa (em watts)
        orcamento.calcularQuantidadePlacas(potenciaPlaca);
        orcamento.calcularCustoTotal();
    }

    // Calcular orçamento Off-Grid
    private void calcularOrcamentoOffGrid(OrcamentoOffGridTO orcamento) {
        double potenciaPlaca = 400.0; // Exemplo: potência de cada placa (em watts)
        orcamento.calcularQuantidadePlacas(potenciaPlaca);
        orcamento.calcularCustoTotal();
    }

    // Buscar orçamento por ID
    public OrcamentoTO findOrcamentoById(Long id) {
        return orcamentos.stream()
                .filter(o -> o.getCodigo().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Buscar todos os orçamentos
    public ArrayList<OrcamentoTO> findAllOrcamentos() {
        return orcamentos;
    }

    // Excluir orçamento
    public boolean deleteOrcamento(Long id) {
        OrcamentoTO existente = findOrcamentoById(id);
        if (existente != null) {
            return orcamentos.remove(existente);
        }
        return false;
    }
}
