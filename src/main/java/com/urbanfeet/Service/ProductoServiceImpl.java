package com.urbanfeet.Service;

import com.urbanfeet.DAO.ProductoDAO;
import com.urbanfeet.Entity.Producto;
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
        if (p.getNombre() == null || p.getNombre().isBlank())
            throw new IllegalArgumentException("El nombre es obligatorio");

        if (p.getVariaciones() == null || p.getVariaciones().isEmpty())
            throw new IllegalArgumentException("Debe agregar al menos una variación de producto");

        for (var v : p.getVariaciones()) {
            if (v.getPrecio() == null || v.getPrecio() <= 0)
                throw new IllegalArgumentException("El precio de una variación debe ser > 0");
            if (v.getStock() == null || v.getStock() < 0)
                throw new IllegalArgumentException("El stock no puede ser negativo");
            if (v.getColor() == null || v.getColor().isBlank())
                throw new IllegalArgumentException("Cada variación debe tener un color");
            if (v.getImageUrl() == null || v.getImageUrl().isBlank())
                throw new IllegalArgumentException("Cada variación debe tener una imagen");
        }

        if (esNuevo) {
            boolean existe = dao.filtrarCatalogo(
                            null, null, null, null, null, null, false,
                            PageRequest.of(0, 1))
                    .getContent()
                    .stream()
                    .anyMatch(prod -> prod.getNombre().equalsIgnoreCase(p.getNombre()));

            if (existe) {
                throw new IllegalArgumentException("Ya existe un producto con el mismo nombre");
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

        // Relación inversa para variaciones
        producto.getVariaciones().forEach(v -> v.setProducto(producto));

        return dao.guardar(producto);
    }

    @Override
    @Transactional
    public Producto actualizar(int id, Producto producto) {
        producto.setId(id);
        validar(producto, false);

        // Relación inversa para variaciones
        producto.getVariaciones().forEach(v -> v.setProducto(producto));

        return dao.guardar(producto);
    }

    @Override
    public void eliminar(int id) {
        dao.eliminar(id);
    }
}
