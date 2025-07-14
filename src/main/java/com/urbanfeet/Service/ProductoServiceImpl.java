package com.urbanfeet.Service;

import com.urbanfeet.DAO.ProductoDAO;
import com.urbanfeet.Entity.Producto;
import com.urbanfeet.Service.ProductoService;
import com.urbanfeet.Repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoDAO dao;

    /*----------- Reglas de negocio y validaciones ----------*/

    private void validar(Producto p, boolean esNuevo) {
        if (p.getPrecio() == null || p.getPrecio() <= 0)
            throw new IllegalArgumentException("El precio debe ser > 0");

        if (p.getStock() == null || p.getStock() < 0)
            throw new IllegalArgumentException("El stock no puede ser negativo");

        if (p.getNombre() == null || p.getNombre().isBlank())
            throw new IllegalArgumentException("El nombre es obligatorio");

        // Evitar duplicados solo al crear
        if (esNuevo) {
            boolean existe = dao.filtrarCatalogo(
                            null,       // marcas
                            null,       // genero
                            null,       // tipo
                            null,       // color
                            null,       // precioMin
                            null,       // precioMax
                            false,      // soloDisponibles
                            PageRequest.of(0, 1))
                    .getContent()             // <- lista real
                    .stream()
                    .anyMatch(prod -> prod.getNombre()
                            .equalsIgnoreCase(p.getNombre()));

            if (existe) {
                throw new IllegalArgumentException(
                        "Ya existe un producto con el mismo nombre");
            }
        }
    }

    /*------------------- Implementación --------------------*/

    @Override
    public Page<Producto> listarCatalogo(List<String> marcas,
                                         String genero,
                                         String tipo,
                                         String color,
                                         Double precioMin,
                                         Double precioMax,
                                         boolean soloDisponibles,
                                         Pageable pageable) {

        // Solo delega; las reglas de negocio adicionales
        // (p.ej. validar rango) puedes ponerlas aquí.
        return dao.filtrarCatalogo(marcas, genero, tipo, color,
                precioMin, precioMax,
                soloDisponibles, pageable);
    }

    @Override
    public Producto obtener(int id) {
        return dao.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
    }

    @Override
    @Transactional
    public Producto crear(Producto producto) {
        validar(producto, true);
        return dao.guardar(producto);
    }

    @Override
    public Producto actualizar(int id, Producto producto) {
        producto.setId(id);
        validar(producto, false);
        return dao.guardar(producto);
    }

    @Override
    public void eliminar(int id) {
        dao.eliminar(id);
    }
}
