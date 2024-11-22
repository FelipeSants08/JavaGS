package br.com.fiap.bo;

import br.com.fiap.dao.PlacaDAO;
import br.com.fiap.to.PlacaTO;

import java.util.ArrayList;

public class PlacaBO {
    private PlacaDAO placaDAO;

    public ArrayList<PlacaTO> findAllPlacas() {
        placaDAO = new PlacaDAO();
        return placaDAO.findAll();
    }

    public PlacaTO findById(Long codigo) {
        placaDAO = new PlacaDAO();
        return placaDAO.findById(codigo);
    }

    public PlacaTO savePlaca(PlacaTO placa) {
        placaDAO = new PlacaDAO();
        if (placaDAO.save(placa)) {
            return placa;
        }
        return null;
    }

    public boolean deletePlaca(Long codigo) {
        placaDAO = new PlacaDAO();
        return placaDAO.delete(codigo);
    }

    public PlacaTO updatePlaca(PlacaTO placa) {
        placaDAO = new PlacaDAO();
        if (placaDAO.update(placa)) {
            return placa;
        }
        return null;
    }
}
