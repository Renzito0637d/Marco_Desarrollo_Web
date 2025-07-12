package com.urbanfeet.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.urbanfeet.Entity.Direccion;
import com.urbanfeet.Repository.DireccionRepository;

@Repository
public class DireccionDAOImpl implements DireccionDAO{

    @Autowired
    DireccionRepository direccionRepository;

    @Override
    public List<Direccion> listaDireccions() {
        return direccionRepository.findAll();
    }

    @Override
    public Direccion obtenerDirecionPorId(Integer id) {
        return direccionRepository.findById(id).get();
    }

    @Override
    public void guardarDireccion(Direccion direccion) {
        direccionRepository.save(direccion);
    }

    @Override
    public void actualizarDireccion(Direccion direccion) {
        direccionRepository.save(direccion);
    }

    @Override
    public void eliminarDireccion(Integer id) {
        Direccion direccionObj = direccionRepository.findById(id).get();
        direccionRepository.delete(direccionObj);
    }
    
}
