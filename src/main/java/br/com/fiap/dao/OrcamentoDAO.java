package br.com.fiap.dao;

import br.com.fiap.to.OrcamentoTO;
import br.com.fiap.to.OrcamentoOffGridTO;
import br.com.fiap.to.OrcamentoOnGridTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrcamentoDAO extends Repository {

    public ArrayList<OrcamentoTO> findAll() {
        ArrayList<OrcamentoTO> orcamentos = new ArrayList<>();
        String sql = "SELECT * FROM orcamento";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrcamentoTO orcamento;
                if ("OFF_GRID".equalsIgnoreCase(rs.getString("tipo_orcamento"))) {
                    orcamento = new OrcamentoOffGridTO();
                    ((OrcamentoOffGridTO) orcamento).setCustoBaterias(rs.getDouble("custo_baterias"));
                    ((OrcamentoOffGridTO) orcamento).setCustoControladorCarga(rs.getDouble("custo_controlador_carga"));
                } else {
                    orcamento = new OrcamentoOnGridTO();
                    ((OrcamentoOnGridTO) orcamento).setCustoInversor(rs.getDouble("custo_inversor"));
                }
                orcamento.setCodigo(rs.getLong("codigo"));
                orcamento.setConsumoMensal(rs.getDouble("consumo_mensal"));
                orcamento.setCustoTotal(rs.getDouble("custo_total"));
                orcamento.setQuantidadePlacas(rs.getInt("quantidade_placas"));
                orcamento.setTipoOrcamento(rs.getString("tipo_orcamento"));
                orcamentos.add(orcamento);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return orcamentos;
    }

    public OrcamentoTO findById(Long id) {
        String sql = "SELECT * FROM orcamento WHERE codigo = ?";
        OrcamentoTO orcamento = null;
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if ("OFF_GRID".equalsIgnoreCase(rs.getString("tipo_orcamento"))) {
                    orcamento = new OrcamentoOffGridTO();
                    ((OrcamentoOffGridTO) orcamento).setCustoBaterias(rs.getDouble("custo_baterias"));
                    ((OrcamentoOffGridTO) orcamento).setCustoControladorCarga(rs.getDouble("custo_controlador_carga"));
                } else {
                    orcamento = new OrcamentoOnGridTO();
                    ((OrcamentoOnGridTO) orcamento).setCustoInversor(rs.getDouble("custo_inversor"));
                }
                orcamento.setCodigo(rs.getLong("codigo"));
                orcamento.setConsumoMensal(rs.getDouble("consumo_mensal"));
                orcamento.setCustoTotal(rs.getDouble("custo_total"));
                orcamento.setQuantidadePlacas(rs.getInt("quantidade_placas"));
                orcamento.setTipoOrcamento(rs.getString("tipo_orcamento"));
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return orcamento;
    }

    public boolean save(OrcamentoTO orcamento) {
        String sql = "INSERT INTO orcamento (consumo_mensal, custo_total, quantidade_placas, tipo_orcamento, " +
                (orcamento instanceof OrcamentoOffGridTO ?
                        "custo_baterias, custo_controlador_carga) VALUES (?, ?, ?, ?, ?, ?)" :
                        "custo_inversor) VALUES (?, ?, ?, ?, ?)");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setDouble(1, orcamento.getConsumoMensal());
            ps.setDouble(2, orcamento.getCustoTotal());
            ps.setInt(3, orcamento.getQuantidadePlacas());
            ps.setString(4, orcamento.getTipoOrcamento());
            if (orcamento instanceof OrcamentoOffGridTO) {
                ps.setDouble(5, ((OrcamentoOffGridTO) orcamento).getCustoBaterias());
                ps.setDouble(6, ((OrcamentoOffGridTO) orcamento).getCustoControladorCarga());
            } else {
                ps.setDouble(5, ((OrcamentoOnGridTO) orcamento).getCustoInversor());
            }
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM orcamento WHERE codigo = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }
    public boolean update(OrcamentoTO orcamento) {
        String sql = "UPDATE orcamento SET consumo_mensal = ?, custo_total = ?, quantidade_placas = ?, tipo_orcamento = ?, " +
                (orcamento instanceof OrcamentoOffGridTO ?
                        "custo_baterias = ?, custo_controlador_carga = ? " :
                        "custo_inversor = ? ") +
                "WHERE codigo = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setDouble(1, orcamento.getConsumoMensal());
            ps.setDouble(2, orcamento.getCustoTotal());
            ps.setInt(3, orcamento.getQuantidadePlacas());
            ps.setString(4, orcamento.getTipoOrcamento());

            if (orcamento instanceof OrcamentoOffGridTO) {
                ps.setDouble(5, ((OrcamentoOffGridTO) orcamento).getCustoBaterias());
                ps.setDouble(6, ((OrcamentoOffGridTO) orcamento).getCustoControladorCarga());
                ps.setLong(7, orcamento.getCodigo());
            } else if (orcamento instanceof OrcamentoOnGridTO) {
                ps.setDouble(5, ((OrcamentoOnGridTO) orcamento).getCustoInversor());
                ps.setLong(6, orcamento.getCodigo());
            }

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar orçamento: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

}
