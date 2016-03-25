-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-03-2016 a las 02:12:56
-- Versión del servidor: 10.1.10-MariaDB
-- Versión de PHP: 7.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hotel`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudades`
--

CREATE TABLE `ciudades` (
  `id_ciudad` int(11) NOT NULL,
  `nombre_ciudad` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_orden_servicio`
--

CREATE TABLE `detalle_orden_servicio` (
  `id_detalle_o_s` int(11) NOT NULL,
  `id_servicio` int(11) NOT NULL,
  `id_orden_servicio` int(11) NOT NULL,
  `cantidad` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `establecimientos`
--

CREATE TABLE `establecimientos` (
  `id_establecimiento` int(11) NOT NULL,
  `razon_social` varchar(200) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `nit` varchar(15) NOT NULL,
  `digito_verificacion` varchar(2) NOT NULL,
  `regimen_comun` tinyint(1) NOT NULL,
  `id_resolucion` int(11) NOT NULL,
  `iva` tinyint(1) NOT NULL,
  `cree` double NOT NULL,
  `id_ciudad` int(11) NOT NULL,
  `logo` longblob NOT NULL,
  `correo` varchar(200) NOT NULL,
  `sitio_web` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `id_factura` int(11) NOT NULL,
  `id_orden_servicio` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `estado` varchar(60) NOT NULL,
  `subtotal` double NOT NULL,
  `iva` double NOT NULL,
  `descuento` double NOT NULL,
  `total` double NOT NULL,
  `id_establecimiento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habitaciones`
--

CREATE TABLE `habitaciones` (
  `id_habitacion` int(11) NOT NULL,
  `habitacion` varchar(30) NOT NULL,
  `id_tipo_habitacion` int(11) NOT NULL,
  `disponibilidad` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `habitaciones`
--

INSERT INTO `habitaciones` (`id_habitacion`, `habitacion`, `id_tipo_habitacion`, `disponibilidad`) VALUES
(1, '001', 1, 0),
(2, '002', 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nacionalidades`
--

CREATE TABLE `nacionalidades` (
  `id_nacionalidad` int(11) NOT NULL,
  `nacionalidad` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `nacionalidades`
--

INSERT INTO `nacionalidades` (`id_nacionalidad`, `nacionalidad`) VALUES
(1, 'Colombia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordenes_servicio`
--

CREATE TABLE `ordenes_servicio` (
  `id_orden_servicio` int(11) NOT NULL,
  `id_reserva` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro_ingresos`
--

CREATE TABLE `registro_ingresos` (
  `id_registro` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `ip` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas_habitaciones`
--

CREATE TABLE `reservas_habitaciones` (
  `id_reserva` int(11) NOT NULL,
  `id_habitacion` int(11) NOT NULL,
  `fecha_llegada` date NOT NULL,
  `fecha_salida` date NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `estado_reserva` varchar(30) NOT NULL,
  `personas` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `reservas_habitaciones`
--

INSERT INTO `reservas_habitaciones` (`id_reserva`, `id_habitacion`, `fecha_llegada`, `fecha_salida`, `id_usuario`, `estado_reserva`, `personas`) VALUES
(1, 2, '2016-03-23', '2016-03-30', 1, 'completa', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resoluciones_dian`
--

CREATE TABLE `resoluciones_dian` (
  `id_resolucion` int(11) NOT NULL,
  `resolucion_dian` varchar(100) NOT NULL,
  `fecha_resolucion` date NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `consecutivo_final` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id_rol` int(11) NOT NULL,
  `rol` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id_rol`, `rol`) VALUES
(1, 'cliente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicios`
--

CREATE TABLE `servicios` (
  `id_servicio` int(11) NOT NULL,
  `servicio` varchar(60) NOT NULL,
  `detalle` text NOT NULL,
  `precio` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_habitaciones`
--

CREATE TABLE `tipos_habitaciones` (
  `id_tipo_habitacion` int(11) NOT NULL,
  `tipo_habitacion` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipos_habitaciones`
--

INSERT INTO `tipos_habitaciones` (`id_tipo_habitacion`, `tipo_habitacion`) VALUES
(1, 'Sencilla'),
(2, 'Cabaña');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `tipo_documento` varchar(30) NOT NULL,
  `documento` varchar(12) NOT NULL,
  `nombres` varchar(60) NOT NULL,
  `apellidos` varchar(60) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `iva` tinyint(1) NOT NULL,
  `retefuente` tinyint(1) NOT NULL,
  `id_rol` int(11) NOT NULL,
  `id_nacionalidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `tipo_documento`, `documento`, `nombres`, `apellidos`, `fecha_nacimiento`, `direccion`, `telefono`, `correo`, `password`, `iva`, `retefuente`, `id_rol`, `id_nacionalidad`) VALUES
(1, 'Cedula', '1113626301', 'mauricio', 'herrera', '1987-03-31', NULL, NULL, NULL, '123465', 0, 0, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `versiones`
--

CREATE TABLE `versiones` (
  `id_version` int(11) NOT NULL,
  `version` varchar(6) NOT NULL,
  `lanzamiento` date NOT NULL,
  `novdades` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ciudades`
--
ALTER TABLE `ciudades`
  ADD PRIMARY KEY (`id_ciudad`);

--
-- Indices de la tabla `detalle_orden_servicio`
--
ALTER TABLE `detalle_orden_servicio`
  ADD PRIMARY KEY (`id_detalle_o_s`),
  ADD KEY `id_servicio` (`id_servicio`),
  ADD KEY `id_orden_servicio` (`id_orden_servicio`);

--
-- Indices de la tabla `establecimientos`
--
ALTER TABLE `establecimientos`
  ADD PRIMARY KEY (`id_establecimiento`),
  ADD KEY `id_resolucion_dian` (`id_resolucion`),
  ADD KEY `id_ciudad` (`id_ciudad`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`id_factura`),
  ADD KEY `id_orden_servicio` (`id_orden_servicio`),
  ADD KEY `id_establecimiento` (`id_establecimiento`);

--
-- Indices de la tabla `habitaciones`
--
ALTER TABLE `habitaciones`
  ADD PRIMARY KEY (`id_habitacion`),
  ADD KEY `id_tipo_habitacion` (`id_tipo_habitacion`);

--
-- Indices de la tabla `nacionalidades`
--
ALTER TABLE `nacionalidades`
  ADD PRIMARY KEY (`id_nacionalidad`);

--
-- Indices de la tabla `ordenes_servicio`
--
ALTER TABLE `ordenes_servicio`
  ADD PRIMARY KEY (`id_orden_servicio`),
  ADD KEY `id_reserva` (`id_reserva`);

--
-- Indices de la tabla `registro_ingresos`
--
ALTER TABLE `registro_ingresos`
  ADD PRIMARY KEY (`id_registro`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `reservas_habitaciones`
--
ALTER TABLE `reservas_habitaciones`
  ADD PRIMARY KEY (`id_reserva`),
  ADD KEY `id_habitacion` (`id_habitacion`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `resoluciones_dian`
--
ALTER TABLE `resoluciones_dian`
  ADD PRIMARY KEY (`id_resolucion`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id_rol`);

--
-- Indices de la tabla `servicios`
--
ALTER TABLE `servicios`
  ADD PRIMARY KEY (`id_servicio`);

--
-- Indices de la tabla `tipos_habitaciones`
--
ALTER TABLE `tipos_habitaciones`
  ADD PRIMARY KEY (`id_tipo_habitacion`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `documento` (`documento`),
  ADD KEY `id_rol` (`id_rol`),
  ADD KEY `id_nacionalidad` (`id_nacionalidad`);

--
-- Indices de la tabla `versiones`
--
ALTER TABLE `versiones`
  ADD PRIMARY KEY (`id_version`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `detalle_orden_servicio`
--
ALTER TABLE `detalle_orden_servicio`
  MODIFY `id_detalle_o_s` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `establecimientos`
--
ALTER TABLE `establecimientos`
  MODIFY `id_establecimiento` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `id_factura` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `habitaciones`
--
ALTER TABLE `habitaciones`
  MODIFY `id_habitacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `nacionalidades`
--
ALTER TABLE `nacionalidades`
  MODIFY `id_nacionalidad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `ordenes_servicio`
--
ALTER TABLE `ordenes_servicio`
  MODIFY `id_orden_servicio` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `registro_ingresos`
--
ALTER TABLE `registro_ingresos`
  MODIFY `id_registro` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `reservas_habitaciones`
--
ALTER TABLE `reservas_habitaciones`
  MODIFY `id_reserva` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `resoluciones_dian`
--
ALTER TABLE `resoluciones_dian`
  MODIFY `id_resolucion` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `servicios`
--
ALTER TABLE `servicios`
  MODIFY `id_servicio` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `tipos_habitaciones`
--
ALTER TABLE `tipos_habitaciones`
  MODIFY `id_tipo_habitacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `versiones`
--
ALTER TABLE `versiones`
  MODIFY `id_version` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
