package cl.inreqtech.infractech.repository;

import cl.inreqtech.infractech.entity.InfraccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InfraccionRepository extends JpaRepository<InfraccionEntity, Integer> {

    @Query("SELECT IE FROM InfraccionEntity IE " +
            "INNER JOIN UsuarioEntity UE ON UE.id = IE.idUsuario " +
            "WHERE UE.rut = ?1")
    public List<InfraccionEntity> findAllByRutUsuario(String rut);
}
