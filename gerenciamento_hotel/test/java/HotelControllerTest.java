import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class HotelControllerTest {

    @Mock
    private ReservaRepository reservaRepository; // Supondo que você tenha um repositório de reservas

    @Mock
    private ClienteRepository clienteRepository; // Supondo que você tenha um repositório de clientes

    @InjectMocks
    private HotelController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    public void testListarReservas() {
        // Arrange
        Reserva reserva1 = new Reserva("12345678900", 1, LocalDate.now(), LocalDate.now().plusDays(2), "reserva1");
        Reserva reserva2 = new Reserva("09876543211", 2, LocalDate.now().plusDays(1), LocalDate.now().plusDays(3), "reserva2");
        when(reservaRepository.findAll()).thenReturn(Arrays.asList(reserva1, reserva2)); // Mockando o retorno

        // Act
        List<Reserva> reservas = controller.listarReservas();

        // Assert
        assertEquals(2, reservas.size(), "O número de reservas retornadas deve ser 2.");
        verify(reservaRepository, times(1)).findAll(); // Verifica se o método foi chamado uma vez
    }

    @Test
    public void testReservarQuarto() {
        // Arrange
        Reserva novaReserva = new Reserva("12345678900", 1, LocalDate.now(), LocalDate.now().plusDays(2), "reserva1");
        when(clienteRepository.existsByCpf(novaReserva.getCpfCliente())).thenReturn(true); // Cliente cadastrado
        when(reservaRepository.save(novaReserva)).thenReturn(novaReserva); // Mockando a reserva

        // Act
        controller.reservarQuarto(novaReserva);

        // Assert
        verify(clienteRepository, times(1)).existsByCpf(novaReserva.getCpfCliente());
        verify(reservaRepository, times(1)).save(novaReserva); // Verifica se a reserva foi salva
    }

    @Test
    public void testIsCpfCadastrado() {
        // Arrange
        String cpf = "12345678900";
        when(clienteRepository.existsByCpf(cpf)).thenReturn(true); // Mockando cliente cadastrado

        // Act
        boolean isCadastrado = controller.isCpfCadastrado(cpf);

        // Assert
        assertTrue(isCadastrado, "O CPF deve estar cadastrado.");
        verify(clienteRepository, times(1)).existsByCpf(cpf);
    }
}
