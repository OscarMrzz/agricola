Objetivo: Evaluar la capacidad de los estudiantes para replicar y adaptar la arquitectura de paquetes (Config, Modelo, DAO, Vista, Reportes) de un dominio comercial a uno completamente diferente en un tiempo controlado.

Contexto del Negocio
Una distribuidora de suministros agrícolas de la región central necesita controlar las órdenes de despacho de insumos para las fincas locales.

Requerimientos de Código y Base de Datos (DAO):
1. Módulo de Control de Productos y Lotes
Operación: Mapear en el paquete modelo la clase Producto (Semillas, Fertilizantes, Herramientas de cultivo).

Regla de Negocio: Cada producto cuenta con una fecha de vencimiento. El ProductoDAO debe proveer un método que liste únicamente los insumos aptos para la venta, alertando en la interfaz visual aquellos que estén a menos de 30 días de expirar.

2. Módulo de Clientes y Créditos Flexibles
Operación: Implementar una interfaz donde se registren los productores locales de la zona.

Regla de Negocio: Cada cliente posee un campo llamado Limite_Credito en Lempiras (Lps). Al registrar una venta al crédito, el sistema verificará el saldo acumulado en su historial. Si la nueva factura supera el límite permitido, el sistema detendrá el proceso de almacenamiento arrojando una alerta controlada mediante excepciones.

3. Módulo transaccional: Facturación (Detalle de Venta)
Operación: Gestión de órdenes de compra directas en caliente a través de un JTable.

Regla: Al ingresar un artículo a la cuadrícula, se calculará de forma automática el Impuesto sobre la Venta (ISV del 15%) y el subtotal acumulado en variables locales de Java antes de ejecutar el bloque try-with-resources para insertar el registro maestro-detalle.

Requerimiento de Reportería (JasperReports):
Factura Formal de Compra: Generación instantánea en PDF mediante JasperReports de la factura recién procesada, mostrando datos generales del cliente, el listado de productos adquiridos con sus subtotales y las firmas de despacho.

Reporte Estadístico de Insumos Críticos: Un reporte global que muestre de forma visual los productos con stock bajo (inferiores al stock mínimo de alerta), ordenados por departamento de origen.

Aspectos Clave que usted evaluará en ambas actividades:
Estructura Estricta de Paquetes: Ningún archivo de la capa vista puede instanciar objetos Connection ni contener sentencias SQL del tipo "SELECT * FROM...".

Uso de Clases de Modelo en Controles Swing: Verificar que pasen objetos del tipo Empleado o Producto directamente a los componentes (como el JComboBox), forzando el polimorfismo mediante la sobreescritura del método @Override toString().

Estabilidad del Sistema: La aplicación no puede colapsar ante ingresos erróneos de datos en los campos numéricos