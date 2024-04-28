package com.example.demo.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Paciente;
import com.example.demo.repository.PacienteRepository;
import com.example.demo.service.PacienteServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceTest {
    @InjectMocks
    private PacienteServiceImpl pacienteService;

    @Mock
    private PacienteRepository pacienteRepositoryMock;

    @Test
    public void listarPacientesTest() {
        //ARRANGE (CREA UN OBJETO PACIENTE)
        Paciente paciente1 = new Paciente();
        paciente1.setRut("12345678-9");
        paciente1.setNombreCompleto("Juan Perez");
        paciente1.setFechaNacimiento(LocalDate.of(1990, 12, 1));

        Paciente paciente2 = new Paciente();
        paciente2.setRut("33333333-3");
        paciente2.setNombreCompleto("Pedro Perez");
        paciente2.setFechaNacimiento(LocalDate.of(1990, 11, 1));

        List<Paciente> pacientes = new ArrayList<>();
        pacientes.add(paciente1);
        pacientes.add(paciente2);

        when(pacienteRepositoryMock.findAll()).thenReturn(pacientes);

        List<Paciente> resultado = pacienteService.getAllPacientes();

        //ASSERT (VERIFICAR QUE SE LISTARON LOS PACIENTES)
        assertNotNull(resultado,"La lista de pacientes no puede ser nula");
        assertEquals(2,resultado.size(),"La lista de pacientes debe tener 2 elementos");

        //VERIFICAR EL PRIMER PACIENTE
        Paciente primerPaciente = resultado.get(0);
        assertEquals(paciente1.getId(), primerPaciente.getId());
        assertEquals("12345678-9", primerPaciente.getRut());
        assertEquals("Juan Perez", primerPaciente.getNombreCompleto());
        assertEquals(LocalDate.of(1990, 12, 1), primerPaciente.getFechaNacimiento());

        //VERIFICAR EL SEGUNDO PACIENTE
        Paciente segundoPaciente = resultado.get(1);
        assertEquals(paciente2.getId(), segundoPaciente.getId());
        assertEquals("33333333-3", segundoPaciente.getRut());
        assertEquals("Pedro Perez", segundoPaciente.getNombreCompleto());
        assertEquals(LocalDate.of(1990, 11, 1), segundoPaciente.getFechaNacimiento());

    }

    
}
