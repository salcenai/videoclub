-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema videoclub
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema videoclub
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `videoclub` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `videoclub` ;

-- -----------------------------------------------------
-- Table `videoclub`.`paises`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `videoclub`.`paises` ;

CREATE TABLE IF NOT EXISTS `videoclub`.`paises` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(3) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 695
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX `nombre_UNIQUE` ON `videoclub`.`paises` (`nombre` ASC) VISIBLE;

CREATE UNIQUE INDEX `codigo_UNIQUE` ON `videoclub`.`paises` (`codigo` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `videoclub`.`peliculas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `videoclub`.`peliculas` ;

CREATE TABLE IF NOT EXISTS `videoclub`.`peliculas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_pais` INT NULL DEFAULT NULL,
  `titulo_compacto` VARCHAR(100) NOT NULL,
  `titulo` VARCHAR(200) NOT NULL,
  `anio` INT NULL DEFAULT NULL,
  `duracion` INT NULL DEFAULT NULL,
  `sinopsis` VARCHAR(500) NULL DEFAULT NULL,
  `fecha_alta` DATETIME NULL DEFAULT NULL,
  `puntuacion_media` INT NULL DEFAULT NULL,
  `num_votos` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_peliculas_paises_id`
    FOREIGN KEY (`id_pais`)
    REFERENCES `videoclub`.`paises` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 82
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX `titulo_compacto_UNIQUE` ON `videoclub`.`peliculas` (`titulo_compacto` ASC) VISIBLE;

CREATE INDEX `fk_peliculas_paises_id_idx` ON `videoclub`.`peliculas` (`id_pais` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `videoclub`.`tipos_estado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `videoclub`.`tipos_estado` ;

CREATE TABLE IF NOT EXISTS `videoclub`.`tipos_estado` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(10) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX `name_UNIQUE` ON `videoclub`.`tipos_estado` (`nombre` ASC) VISIBLE;

CREATE UNIQUE INDEX `codigo_UNIQUE` ON `videoclub`.`tipos_estado` (`codigo` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `videoclub`.`usuarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `videoclub`.`usuarios` ;

CREATE TABLE IF NOT EXISTS `videoclub`.`usuarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(254) NOT NULL,
  `contrasena` VARCHAR(128) NOT NULL,
  `activo` TINYINT(1) NOT NULL DEFAULT '1',
  `fecha_alta` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_baja` DATETIME NULL DEFAULT NULL,
  `rol` VARCHAR(45) NOT NULL DEFAULT 'USER',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX `email_UNIQUE` ON `videoclub`.`usuarios` (`correo` ASC) VISIBLE;

CREATE UNIQUE INDEX `username_UNIQUE` ON `videoclub`.`usuarios` (`nombre` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `videoclub`.`estados`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `videoclub`.`estados` ;

CREATE TABLE IF NOT EXISTS `videoclub`.`estados` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_pelicula` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  `id_tipo_estado` INT NULL DEFAULT NULL,
  `puntuacion` INT NULL DEFAULT NULL,
  `fecha` DATETIME NULL DEFAULT NULL,
  `critica` VARCHAR(254) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_estados_peliculas_id`
    FOREIGN KEY (`id_pelicula`)
    REFERENCES `videoclub`.`peliculas` (`id`),
  CONSTRAINT `fk_estados_tipos_estado_id`
    FOREIGN KEY (`id_tipo_estado`)
    REFERENCES `videoclub`.`tipos_estado` (`id`),
  CONSTRAINT `fk_estados_usuarios_id`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `videoclub`.`usuarios` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `fk_estados_peliculas_id_idx` ON `videoclub`.`estados` (`id_pelicula` ASC) VISIBLE;

CREATE INDEX `fk_estados_usuarios_id_idx` ON `videoclub`.`estados` (`id_usuario` ASC) VISIBLE;

CREATE INDEX `fk_estados_tipos_estado_id_idx` ON `videoclub`.`estados` (`id_tipo_estado` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `videoclub`.`generos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `videoclub`.`generos` ;

CREATE TABLE IF NOT EXISTS `videoclub`.`generos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `prioridad` INT NOT NULL,
  `codigo` VARCHAR(5) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 632
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX `name_UNIQUE` ON `videoclub`.`generos` (`nombre` ASC) VISIBLE;

CREATE UNIQUE INDEX `codigo_UNIQUE` ON `videoclub`.`generos` (`codigo` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `videoclub`.`generos_pelicula`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `videoclub`.`generos_pelicula` ;

CREATE TABLE IF NOT EXISTS `videoclub`.`generos_pelicula` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `orden` INT NOT NULL,
  `id_pelicula` INT NOT NULL,
  `id_genero` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_generos_pelicula_generos_id`
    FOREIGN KEY (`id_genero`)
    REFERENCES `videoclub`.`generos` (`id`),
  CONSTRAINT `fk_generos_pelicula_pelicula_id`
    FOREIGN KEY (`id_pelicula`)
    REFERENCES `videoclub`.`peliculas` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 174
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `fk_generos_pelicula_pelicula_id_idx` ON `videoclub`.`generos_pelicula` (`id_pelicula` ASC) VISIBLE;

CREATE INDEX `fk_generos_pelicula_generos_id_idx` ON `videoclub`.`generos_pelicula` (`id_genero` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
