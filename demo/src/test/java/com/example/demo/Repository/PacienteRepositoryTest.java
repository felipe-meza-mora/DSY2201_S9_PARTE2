package com.example.demo.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Paciente;
import com.example.demo.repository.PacienteRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PacienteRepositoryTest {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Test
    public void guardarPacienteTest(){
        //ARRANGE (CREA UN OBJETO PACIENTE)
        Paciente paciente = new Paciente();
        paciente.setRut("12345678-9");
        paciente.setNombreCompleto("Juan Perez");
        paciente.setFechaNacimiento(LocalDate.of(1990, 12, 1));

        //ACT (GUARDAR EL PACIENTE)
        Paciente resultado = pacienteRepository.save(paciente);

        //ASSERT (VERIFICAR QUE SE GUARDO EL PACIENTE)
        assertNotNull(resultado.getId());
        assertEquals("12345678-9", resultado.getRut());
        assertEquals("Juan Perez", resultado.getNombreCompleto());
        assertEquals(LocalDate.of(1990, 12, 1), resultado.getFechaNacimiento());
        
    }

    @Test
    public void modificarPacienteTest(){
        //ARRANGE (CREA UN OBJETO PACIENTE)
        Paciente paciente = new Paciente();
        paciente.setRut("12345678-9");
        paciente.setNombreCompleto("Juan Perez");
        paciente.setFechaNacimiento(LocalDate.of(1990, 12, 1));

        //ACT (GUARDAR EL PACIENTE)
        Paciente resultado = pacienteRepository.save(paciente);

        //ASSERT (VERIFICAR QUE SE GUARDO EL PACIENTE)
        assertNotNull(resultado.getId());
        assertEquals("Juan Perez", resultado.getNombreCompleto());
        assertEquals("12345678-9", resultado.getRut());
        assertEquals(LocalDate.of(1990, 12, 1), resultado.getFechaNacimiento());

        //MODIFICAR EL PACIENTE
        resultado.setNombreCompleto("Juanito Perez");
        resultado.setRut("12345678-9");
        resultado.setFechaNacimiento(LocalDate.of(1990, 12, 1));

        //ACT (GUARDAR EL PACIENTE MODIFICADO)
        Paciente resultadoModificado = pacienteRepository.save(resultado);

        //ASSERT (VERIFICAR QUE SE MODIFICO EL PACIENTE)
        assertNotNull(resultadoModificado.getId());
        assertEquals("12345678-9", resultadoModificado.getRut());
        assertEquals("Juanito Perez", resultadoModificado.getNombreCompleto());
        assertEquals(LocalDate.of(1990, 12, 1), resultadoModificado.getFechaNacimiento());
    }
    
    @Test
    public void buscarPacienteTest(){
        //ARRANGE (CREA UN OBJETO PACIENTE)
        Paciente paciente = new Paciente();
        paciente.setRut("12345678-9");
        paciente.setNombreCompleto("Juan Perez");
        paciente.setFechaNacimiento(LocalDate.of(1990, 12, 1));

        //ACT (GUARDAR EL PACIENTE)
        Paciente resultado = pacienteRepository.save(paciente);

        //ACT (BUSCAR EL PACIENTE)
        Optional<Paciente> resultadoBusqueda = pacienteRepository.findById(resultado.getId());

        //ASSERT (VERIFICAR QUE SE ENCONTRO EL PACIENTE)
        assertNotNull(resultadoBusqueda);
        assertEquals("12345678-9", resultadoBusqueda.get().getRut());
        assertEquals("Juan Perez", resultadoBusqueda.get().getNombreCompleto());
        assertEquals(LocalDate.of(1990, 12, 1), resultadoBusqueda.get().getFechaNacimiento());
    }
    
    @Test
    public void eliminarPacienteTest(){
        //ARRANGE (CREA UN OBJETO PACIENTE)
        Paciente paciente = new Paciente();
        paciente.setRut("12345678-9");
        paciente.setNombreCompleto("Juan Perez");
        paciente.setFechaNacimiento(LocalDate.of(1990, 12, 1));

        //ACT (GUARDAR EL PACIENTE)
        Paciente resultado = pacienteRepository.save(paciente);

        //ACT (ELIMINAR EL PACIENTE)
        pacienteRepository.delete(resultado);

        //ASSERT (VERIFICAR QUE SE ELIMINO EL PACIENTE)
        Optional<Paciente> resultadoEliminado = pacienteRepository.findById(resultado.getId());
        assertFalse(resultadoEliminado.isPresent(),"El paciente no se ha eliminado");
    }

    
}
