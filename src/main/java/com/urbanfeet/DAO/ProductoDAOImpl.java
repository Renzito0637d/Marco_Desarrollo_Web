package com.urbanfeet.DAO;

import com.urbanfeet.DAO.ProductoDAO;
import com.urbanfeet.Entity.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class ProductoDAOImpl implements ProductoDAO {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Page<Producto> filtrarCatalogo(List<String> marcas,
                                          String genero,
                                          String tipo,
                                          String color,
                                          Double precioMin,
                                          Double precioMax,
                                          boolean soloDisponibles,
                                          Pageable pageable) {

        StringBuilder jpql = new StringBuilder("SELECT p FROM Producto p WHERE 1=1 ");
        Map<String, Object> params = new HashMap<>();

        if (marcas != null && !marcas.isEmpty()) {           // List no usa isBlank()
            jpql.append("AND LOWER(p.marca) IN :marcas ");   // IN ( :lista )
            params.put("marcas", marcas);                    // misma clave
        }

        if (genero != null && !genero.isBlank()) {
            jpql.append("AND LOWER(p.genero) = LOWER(:genero) ");
            params.put("genero", genero);
        }

        if (tipo != null && !tipo.isBlank()) {
            jpql.append("AND LOWER(p.tipo) = LOWER(:tipo) ");
            params.put("tipo", tipo);
        }
        if (precioMin != null) {
            jpql.append("AND p.precio >= :min ");
            params.put("min", precioMin);
        }
        if (precioMax != null) {
            jpql.append("AND p.precio <= :max ");
            params.put("max", precioMax);
        }
        if (soloDisponibles) {
            jpql.append("AND p.stock > 0 ");
        }

        // Ordenamiento dinámico
        if (pageable != null && pageable.getSort().isSorted()) {
            jpql.append("ORDER BY ");
            pageable.getSort().forEach(order ->
                    jpql.append("p.").append(order.getProperty())
                            .append(order.isAscending() ? " ASC, " : " DESC, "));
            jpql.setLength(jpql.length() - 2); // Eliminar la última coma
        }

        TypedQuery<Producto> query = em.createQuery(jpql.toString(), Producto.class);
        params.forEach(query::setParameter);

        // Paginación manual
        int total = query.getResultList().size();
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        return new PageImpl<>(query.getResultList(), pageable, total);
    }

    @Override
    public Optional<Producto> buscarPorId(int id) {
        return Optional.ofNullable(em.find(Producto.class, id));
    }

    @Override
    @Transactional
    public Producto guardar(Producto producto) {
        if (producto.getId() == null) {
            em.persist(producto);
            return producto;
        }
        return em.merge(producto);
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        buscarPorId(id).ifPresent(em::remove);
    }
}
