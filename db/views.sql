USE db_agricola;
GO

CREATE OR ALTER VIEW vista_facturas_venta AS
SELECT
    v.no_factura,
    MIN(v.fecha_venta) AS fecha_venta,
    MAX(CONCAT(c.nombre_cliente, ' ', c.apellido_cliente)) AS cliente,
    SUM(v.precio_antes_impuesto * v.cantidad_producto) AS subtotal,
    SUM(v.impuesto) AS impuesto,
    SUM(v.total_pagar) AS total
FROM ventas v
INNER JOIN clientes c ON v.id_cliente = c.id_cliente
GROUP BY v.no_factura;
GO

CREATE OR ALTER VIEW vista_ventas_detalle AS
SELECT
    v.id_venta,
    v.no_factura,
    CONCAT(c.nombre_cliente, ' ', c.apellido_cliente) AS cliente,
    v.fecha_venta,
    p.nombre_producto,
    v.cantidad_producto,
    v.metodo_pago,
    v.precio_antes_impuesto AS precio_unitario,
    (v.precio_antes_impuesto * v.cantidad_producto) AS subtotal,
    v.impuesto,
    v.total_pagar
FROM ventas v
INNER JOIN clientes c ON v.id_cliente = c.id_cliente
INNER JOIN productos p ON v.id_foranea_producto = p.id_producto;
GO

CREATE OR ALTER VIEW vista_compras_detalle AS
SELECT
    co.id_compra,
    co.no_factura,
    co.fecha_compra,
    p.nombre_producto,
    co.cantidad_compra AS cantidad_producto,
    co.metodo_pago,
    co.precio_compra AS precio_unitario,
    (co.precio_compra * co.cantidad_compra) AS total_antes_impuesto,
    (co.precio_compra * co.cantidad_compra) * 0.15 AS impuesto,
    (co.precio_compra * co.cantidad_compra) * 1.15 AS total_a_pagar
FROM compras co
INNER JOIN productos p ON co.id_foranea_producto = p.id_producto;
GO

CREATE OR ALTER VIEW vista_facturas_compra AS
SELECT
    co.no_factura,
    MIN(co.fecha_compra) AS fecha_compra,
    MAX(co.metodo_pago) AS metodo_pago,
    SUM(co.precio_compra * co.cantidad_compra) AS subtotal,
    SUM((co.precio_compra * co.cantidad_compra) * 0.15) AS impuesto,
    SUM((co.precio_compra * co.cantidad_compra) * 1.15) AS total
FROM compras co
GROUP BY co.no_factura;
GO

CREATE OR ALTER VIEW vista_creditos_clientes AS
SELECT
    c.id_cliente,
    c.nombre_cliente,
    c.apellido_cliente,
    c.limite_credito AS credito_maximo,
    ISNULL(SUM(CASE WHEN v.tipo = 'credito' THEN v.total_pagar ELSE 0 END), 0)
        - ISNULL((SELECT SUM(pc.cantidad) FROM pago_credito_cliente pc WHERE pc.id_foranea_cliente = c.id_cliente), 0) AS credito_actual,
    c.limite_credito - (
        ISNULL(SUM(CASE WHEN v.tipo = 'credito' THEN v.total_pagar ELSE 0 END), 0)
        - ISNULL((SELECT SUM(pc.cantidad) FROM pago_credito_cliente pc WHERE pc.id_foranea_cliente = c.id_cliente), 0)
    ) AS diferencia
FROM clientes c
LEFT JOIN ventas v ON c.id_cliente = v.id_cliente
GROUP BY c.id_cliente, c.nombre_cliente, c.apellido_cliente, c.limite_credito;
GO

CREATE OR ALTER VIEW vista_inventario AS
SELECT
    p.id_producto,
    p.nombre_producto,
    ISNULL(SUM(CASE WHEN l.cantidad > 0 THEN l.cantidad ELSE 0 END), 0) AS stock,
    ISNULL(ic.stock_minimo, 0) AS stock_minimo,
    ic.fecha_ultima_entrada,
    ic.fecha_ultima_salida,
    MIN(CASE WHEN l.cantidad > 0 THEN l.fecha_vencimiento END) AS proximo_vencimiento,
    ISNULL(SUM(CASE WHEN l.cantidad > 0 AND l.fecha_vencimiento >= GETDATE()
        AND DATEDIFF(DAY, GETDATE(), l.fecha_vencimiento) <= 30 THEN l.cantidad ELSE 0 END), 0) AS cantidad_por_vencer,
    ISNULL(SUM(CASE WHEN l.cantidad > 0 AND l.fecha_vencimiento < GETDATE() THEN l.cantidad ELSE 0 END), 0) AS cantidad_vencida,
    ISNULL(SUM(CASE WHEN l.cantidad > 0 AND l.fecha_vencimiento >= GETDATE() THEN l.cantidad ELSE 0 END), 0) AS stock_vendible
FROM productos p
LEFT JOIN inventario_config ic ON p.id_producto = ic.id_producto
LEFT JOIN inventario_lote l ON p.id_producto = l.id_producto
GROUP BY p.id_producto, p.nombre_producto, ic.stock_minimo, ic.fecha_ultima_entrada, ic.fecha_ultima_salida;
GO

CREATE OR ALTER VIEW vista_advertencia_vencimiento AS
SELECT
    vi.id_producto,
    vi.nombre_producto,
    vi.cantidad_por_vencer AS cantidad,
    vi.proximo_vencimiento AS fecha_vencimiento,
    DATEDIFF(DAY, GETDATE(), vi.proximo_vencimiento) AS dias_restantes
FROM vista_inventario vi
WHERE vi.cantidad_por_vencer > 0;
GO

CREATE OR ALTER VIEW vista_advertencia_vencidos AS
SELECT
    vi.id_producto,
    vi.nombre_producto,
    vi.cantidad_vencida AS cantidad
FROM vista_inventario vi
WHERE vi.cantidad_vencida > 0;
GO

CREATE OR ALTER VIEW vista_advertencia_stock_bajo AS
SELECT
    vi.id_producto,
    vi.nombre_producto,
    vi.stock AS stock_actual,
    p.departamento_origen
FROM vista_inventario vi
INNER JOIN productos p ON vi.id_producto = p.id_producto
WHERE vi.stock < vi.stock_minimo AND vi.stock_minimo > 0;
GO
