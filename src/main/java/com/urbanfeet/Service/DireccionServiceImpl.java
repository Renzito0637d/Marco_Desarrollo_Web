package com.urbanfeet.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urbanfeet.DAO.DireccionDAO;
import com.urbanfeet.Entity.Direccion;

@Service
public class DireccionServiceImpl implements DireccionService {

    @Autowired
    private DireccionDAO direccionDAO;
    
    @Override
    public List<Direccion> listaDireccions() {
        return direccionDAO.listaDireccions();
    }

    @Override
    public Direccion obtenerDirecionPorId(Integer id) {
        return direccionDAO.obtenerDirecionPorId(id);
    }

    @Override
    public void guardarDireccion(Direccion direccion) {
        direccionDAO.guardarDireccion(direccion);
    }

    @Override
    public void actualizarDireccion(Direccion direccion) {
        direccionDAO.actualizarDireccion(direccion);
    }

    @Override
    public void eliminarDireccion(Integer id) {
        direccionDAO.eliminarDireccion(id);
    }
    
}
