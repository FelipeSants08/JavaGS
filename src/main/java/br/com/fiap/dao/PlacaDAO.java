package br.com.fiap.dao;

import br.com.fiap.to.PlacaTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlacaDAO extends Repository {

    public ArrayList<PlacaTO> findAll() {
        ArrayList<PlacaTO> placas = new ArrayList<>();
        String sql = "SELECT * FROM placa";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PlacaTO placa = new PlacaTO();
                placa.setCodigo(rs.getLong("codigo"));
                placa.setCustoPorPlaca(rs.getDouble("custo_por_placa"));
                placa.setPotenciaPlaca(rs.getDouble("potencia_placa"));
                placas.add(placa);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar placas: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return placas;
    }

    public PlacaTO findById(Long id) {
        String sql = "SELECT * FROM placa WHERE codigo = ?";
        PlacaTO placa = null;
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                placa = new PlacaTO();
                placa.setCodigo(rs.getLong("codigo"));
                placa.setCustoPorPlaca(rs.getDouble("custo_por_placa"));
                placa.setPotenciaPlaca(rs.getDouble("potencia_placa"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar placa por ID: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return placa;
    }

    public boolean save(PlacaTO placa) {
        String sql = "INSERT INTO placa (custo_por_placa, potencia_placa) VALUES (?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setDouble(1, placa.getCustoPorPlaca());
            ps.setDouble(2, placa.getPotenciaPlaca());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao salvar placa: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM placa WHERE codigo = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir placa: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }
    public boolean update(PlacaTO placa) {
        String sql = "UPDATE placa SET custo_por_placa = ?, potencia_placa = ? WHERE codigo = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setDouble(1, placa.getCustoPorPlaca());
            ps.setDouble(2, placa.getPotenciaPlaca());
            ps.setLong(4, placa.getCodigo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar placa: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

}
