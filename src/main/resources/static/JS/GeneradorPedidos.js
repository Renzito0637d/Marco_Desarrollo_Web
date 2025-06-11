function renderPedidos(pedidos) {
    const container = document.getElementById("adminOrdersAccordion");
    container.innerHTML = "";

    pedidos.forEach((pedido, index) => {
        const articulosHtml = pedido.articulos
            .map(
                (art) =>
                    `<li class="list-group-item d-flex justify-content-between align-items-center">
        <span class="articulo-nombre">${art.nombre}</span>
        <span>
            <span class="cantidad-texto">Cantidad: ${art.cantidad}</span>
            <input type="number" class="form-control form-control-sm d-none cantidad-input" value="${art.cantidad}" style="width: 80px;">
        </span>
        </li>`
            )
            .join("");

        const pedidoHtml = `
        <div class="accordion-item pedido-item" id="pedido-${pedido.id}">
        <h2 class="accordion-header" id="adminHeading${index}">
            <button class="accordion-button fw-bold ${index !== 0 ? "collapsed" : ""
            }" type="button" data-bs-toggle="collapse"
            data-bs-target="#adminOrder${index}" aria-expanded="${index === 0
            }" aria-controls="adminOrder${index}">
            Pedido #${pedido.id} - ${pedido.fecha
            } - Total: <span class="text-success ms-2">S/${pedido.total.toFixed(
                2
            )}</span>
            </button>
        </h2>
        <div id="adminOrder${index}" class="accordion-collapse collapse ${index === 0 ? "show" : ""
            }" aria-labelledby="adminHeading${index}"
            data-bs-parent="#adminOrdersAccordion">
            <div class="accordion-body">
            <div class="mb-3">
                <label for="estadoPedido${pedido.id
            }" class="form-label fw-semibold">Estado del pedido:</label>
                <select class="form-select w-auto" id="estadoPedido${pedido.id}">
                ${["Recibido", "Preparando", "Enviado", "Entregado"]
                .map(
                    (e) =>
                        `<option value="${e}" ${e === pedido.estado ? "selected" : ""
                        }>${e}</option>`
                )
                .join("")}
                </select>
            </div>
            <div class="d-flex gap-3 mb-4">
                <button class="btn btn-success px-4" onclick="actualizarEstado('${pedido.id
            }', 'estadoPedido${pedido.id}')">💾 Guardar cambios</button>
                <button class="btn btn-outline-danger px-4" onclick="eliminarPedido('${pedido.id
            }')">🗑️ Eliminar pedido</button>
            </div>
            <div class="row">
                <div class="col-md-6 mb-4">
                <h6 class="fw-bold">🛒 Artículos</h6>
                <ul class="list-group" id="articulos-${pedido.id}">
                    ${articulosHtml}
                </ul>
                </div>
                <div class="col-md-6 mb-4">
                <h6 class="fw-bold">📍 Detalles</h6>
                <ul class="list-group">
                    <li class="list-group-item">
                    Dirección: <span id="direccionTexto-${pedido.id}">${pedido.direccion
            }</span>
                    <input type="text" class="form-control d-none mt-2" id="direccionInput-${pedido.id
            }" value="${pedido.direccion}">
                    </li>
                    <li class="list-group-item">
                    Método de pago: <span id="pagoTexto-${pedido.id}">${pedido.metodoPago
            }</span>
                    <select class="form-select d-none mt-2" id="pagoSelect-${pedido.id
            }">
                        ${["Tarjeta", "Efectivo", "Yape"]
                .map(
                    (m) =>
                        `<option value="${m}" ${m === pedido.metodoPago ? "selected" : ""
                        }>${m}</option>`
                )
                .join("")}
                    </select>
                    </li>
                </ul>
                <div class="mt-3 d-flex gap-2" id="botonesEdicion-${pedido.id}">
                    <button class="btn btn-primary" onclick="activarEdicion('${pedido.id
            }')">✏️ Editar</button>
                </div>
                <div class="mt-3 d-flex gap-2 d-none" id="accionesEdicion-${pedido.id
            }">
                    <button class="btn btn-success" onclick="guardarEdicion('${pedido.id
            }')">💾 Guardar</button>
                    <button class="btn btn-secondary" onclick="cancelarEdicion('${pedido.id
            }')">❌ Cancelar</button>
                </div>
                </div>
            </div>
            </div>
        </div>
        </div>`;

        container.insertAdjacentHTML("beforeend", pedidoHtml);
    });
}
