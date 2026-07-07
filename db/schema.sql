IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'db_agricola')
BEGIN
    CREATE DATABASE db_agricola;
END
GO

USE db_agricola;
GO

IF NOT EXISTS (SELECT name FROM sys.server_principals WHERE name = N'oscar')
BEGIN
    CREATE LOGIN oscar WITH PASSWORD = 'password12345678', CHECK_POLICY = OFF;
END
GO

IF NOT EXISTS (SELECT name FROM sys.database_principals WHERE name = N'oscar')
BEGIN
    CREATE USER oscar FOR LOGIN oscar;
    ALTER ROLE db_owner ADD MEMBER oscar;
END
GO

IF OBJECT_ID('roles', 'U') IS NULL
CREATE TABLE roles (
    id_rol INT IDENTITY(1,1) PRIMARY KEY,
    nombre_rol VARCHAR(100) NOT NULL,
    estado_rol BIT NOT NULL DEFAULT 1
);

IF OBJECT_ID('vistas', 'U') IS NULL
CREATE TABLE vistas (
    id_vista INT IDENTITY(1,1) PRIMARY KEY,
    nombre_vista VARCHAR(100) NOT NULL
);

IF OBJECT_ID('[user]', 'U') IS NULL
CREATE TABLE [user] (
    id_user INT IDENTITY(1,1) PRIMARY KEY,
    nombre_user VARCHAR(100) NOT NULL UNIQUE,
    password_user VARCHAR(255) NOT NULL,
    id_foranea_rol INT NOT NULL,
    estado BIT NOT NULL DEFAULT 1,
    FOREIGN KEY (id_foranea_rol) REFERENCES roles(id_rol)
);

IF OBJECT_ID('permisos_accion', 'U') IS NULL
CREATE TABLE permisos_accion (
    id_permiso_accion INT IDENTITY(1,1) PRIMARY KEY,
    id_foranea_rol INT NOT NULL,
    tabla VARCHAR(100) NOT NULL,
    accion VARCHAR(20) NOT NULL,
    estado BIT NOT NULL DEFAULT 1,
    FOREIGN KEY (id_foranea_rol) REFERENCES roles(id_rol)
);

IF OBJECT_ID('permisos_vista', 'U') IS NULL
CREATE TABLE permisos_vista (
    id_permiso_vista INT IDENTITY(1,1) PRIMARY KEY,
    id_foranea_rol INT NOT NULL,
    id_foranea_vista INT NOT NULL,
    estado BIT NOT NULL DEFAULT 1,
    FOREIGN KEY (id_foranea_rol) REFERENCES roles(id_rol),
    FOREIGN KEY (id_foranea_vista) REFERENCES vistas(id_vista)
);

IF OBJECT_ID('productos', 'U') IS NULL
CREATE TABLE productos (
    id_producto INT IDENTITY(1,1) PRIMARY KEY,
    nombre_producto VARCHAR(150) NOT NULL,
    categoria_producto VARCHAR(100) NOT NULL,
    departamento_origen VARCHAR(100) NOT NULL,
    precio_venta DECIMAL(18,2) NOT NULL,
    fecha_vencimiento DATETIME NOT NULL
);

IF OBJECT_ID('clientes', 'U') IS NULL
CREATE TABLE clientes (
    id_cliente INT IDENTITY(1,1) PRIMARY KEY,
    nombre_cliente VARCHAR(100) NOT NULL,
    apellido_cliente VARCHAR(100) NOT NULL,
    estado BIT NOT NULL DEFAULT 1,
    limite_credito DECIMAL(18,2) NOT NULL DEFAULT 0
);

IF OBJECT_ID('inventario_config', 'U') IS NULL
CREATE TABLE inventario_config (
    id_producto INT PRIMARY KEY,
    stock_minimo INT NOT NULL DEFAULT 0,
    fecha_ultima_entrada DATETIME NULL,
    fecha_ultima_salida DATETIME NULL,
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

IF OBJECT_ID('compras', 'U') IS NULL
CREATE TABLE compras (
    id_compra INT IDENTITY(1,1) PRIMARY KEY,
    id_foranea_producto INT NOT NULL,
    id_foranea_usuario INT NOT NULL,
    cantidad_compra INT NOT NULL,
    precio_compra DECIMAL(18,2) NOT NULL,
    fecha_expiracion DATETIME NULL,
    fecha_compra DATETIME NOT NULL DEFAULT GETDATE(),
    no_factura VARCHAR(50) NULL,
    metodo_pago VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_foranea_producto) REFERENCES productos(id_producto),
    FOREIGN KEY (id_foranea_usuario) REFERENCES [user](id_user)
);

IF OBJECT_ID('inventario_lote', 'U') IS NULL
CREATE TABLE inventario_lote (
    id_lote INT IDENTITY(1,1) PRIMARY KEY,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL DEFAULT 0,
    fecha_vencimiento DATETIME NOT NULL,
    fecha_entrada DATETIME NOT NULL DEFAULT GETDATE(),
    id_compra INT NULL,
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto),
    FOREIGN KEY (id_compra) REFERENCES compras(id_compra)
);

IF OBJECT_ID('retiro_inventario', 'U') IS NULL
CREATE TABLE retiro_inventario (
    id_retiro INT IDENTITY(1,1) PRIMARY KEY,
    id_producto INT NOT NULL,
    id_lote INT NULL,
    cantidad INT NOT NULL,
    motivo VARCHAR(200) NOT NULL,
    fecha_retiro DATETIME NOT NULL DEFAULT GETDATE(),
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto),
    FOREIGN KEY (id_lote) REFERENCES inventario_lote(id_lote),
    FOREIGN KEY (id_usuario) REFERENCES [user](id_user)
);

IF OBJECT_ID('ventas', 'U') IS NULL
CREATE TABLE ventas (
    id_venta INT IDENTITY(1,1) PRIMARY KEY,
    id_foranea_vendedor INT NOT NULL,
    id_foranea_producto INT NOT NULL,
    no_factura VARCHAR(50) NOT NULL,
    fecha_venta DATETIME NOT NULL DEFAULT GETDATE(),
    id_cliente INT NOT NULL,
    cantidad_producto INT NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    metodo_pago VARCHAR(20) NOT NULL,
    precio_antes_impuesto DECIMAL(18,2) NOT NULL,
    impuesto DECIMAL(18,2) NOT NULL,
    total_pagar DECIMAL(18,2) NOT NULL,
    FOREIGN KEY (id_foranea_vendedor) REFERENCES [user](id_user),
    FOREIGN KEY (id_foranea_producto) REFERENCES productos(id_producto),
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);

IF OBJECT_ID('pago_credito_cliente', 'U') IS NULL
CREATE TABLE pago_credito_cliente (
    id_pago_credito INT IDENTITY(1,1) PRIMARY KEY,
    id_foranea_cliente INT NOT NULL,
    fecha_pago DATETIME NOT NULL DEFAULT GETDATE(),
    cantidad DECIMAL(18,2) NOT NULL,
    FOREIGN KEY (id_foranea_cliente) REFERENCES clientes(id_cliente)
);
