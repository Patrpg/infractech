-- -----------------------------------------------------
-- Table `TipoUsuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TipoUsuario`
(
    `Id`     INT         NOT NULL AUTO_INCREMENT,
    `Nombre` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`Id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Usuario`
(
    `Id`              INT         NOT NULL AUTO_INCREMENT,
    `idTipoUsuario`   INT         NOT NULL,
    `Nombres`         VARCHAR(45) NOT NULL,
    `ApellidoPaterno` VARCHAR(45) NOT NULL,
    `ApellidoMaterno` VARCHAR(45) NOT NULL,
    `Rut`             VARCHAR(45) NOT NULL,
    `Pass`            CHAR(60)    NOT NULL,
    PRIMARY KEY (`Id`),
    CONSTRAINT `fk_Usuario_TipoUsuario`
        FOREIGN KEY (`idTipoUsuario`)
            REFERENCES `TipoUsuario` (`Id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Infraccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Infraccion`
(
    `Id`           INT         NOT NULL AUTO_INCREMENT,
    `idUsuario`    INT         NOT NULL,
    `PlacaPatente` VARCHAR(45) NOT NULL,
    `Monto`        INT         NOT NULL,
    `Observacion`  VARCHAR(45) NOT NULL,
    `FechaEmision` DATETIME    NOT NULL,
    `FechaPago`    DATETIME    NULL,
    PRIMARY KEY (`Id`),
    CONSTRAINT `fk_Infraccion_Usuario1`
        FOREIGN KEY (`idUsuario`)
            REFERENCES `Usuario` (`Id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Data for table `mydb`.`TipoUsuario`
-- -----------------------------------------------------
INSERT INTO `TipoUsuario` (`Id`, `Nombre`)
VALUES (1, 'Tipo1');