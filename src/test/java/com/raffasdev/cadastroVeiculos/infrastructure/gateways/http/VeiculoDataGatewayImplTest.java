package com.raffasdev.cadastroVeiculos.infrastructure.gateways.http;

import com.raffasdev.cadastroVeiculos.domain.exception.ServiceUnavailableException;
import com.raffasdev.cadastroVeiculos.infrastructure.gateways.http.dto.response.VeiculoDataGatewayResponse;
import com.raffasdev.cadastroVeiculos.util.VeiculoCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VeiculoDataGatewayImplTest {

    @Mock
    private RestClient restClientMock;

    @Mock
    private RestClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    private RestClient.RequestHeadersSpec requestHeadersSpecMock;

    @Mock
    private RestClient.ResponseSpec responseSpecMock;

    @InjectMocks
    private VeiculoDataGatewayImpl veiculoDataGatewayImpl;


    @Test
    @DisplayName("getVeiculoInfosByPlaca returns VeiculoDataGatewayResponse when successful")
    void getVeiculoInfosByPlaca_ReturnsVeiculoDataGatewayResponse_WhenSuccessful() {

        when(restClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);

        var expectedResponse = VeiculoCreator.createValidVeiculoDataGatewayImplResponse();
        when(responseSpecMock.body(VeiculoDataGatewayResponse.class)).thenReturn(expectedResponse);

        var actualResponse = veiculoDataGatewayImpl.getVeiculoInfosByPlaca("ABC1234");

        verify(restClientMock, times(1)).get();
        verify(requestHeadersUriSpecMock, times(1)).uri(anyString());
        verify(requestHeadersSpecMock, times(1)).retrieve();
        verify(responseSpecMock, times(1)).body(VeiculoDataGatewayResponse.class);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("getVeiculoInfosByPlaca returns throws RestClientException when service is unavailable")
    void getVeiculoInfosByPlaca_ThrowsRestClientException_WhenServiceIsUnavailable() {

        when(restClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);

        when(requestHeadersSpecMock.retrieve()).thenThrow(new RestClientException(
                "Serviço indisponível. Por favor, tente novamente mais tarde."));

        try {
            veiculoDataGatewayImpl.getVeiculoInfosByPlaca("ABC1234");
        } catch (Exception e) {
            assertEquals(RestClientException.class, e.getClass());
            assertEquals(
                    "Serviço indisponível. Por favor, tente novamente mais tarde.", e.getMessage());
        }

        verify(restClientMock, times(1)).get();
        verify(requestHeadersUriSpecMock, times(1)).uri(anyString());
        verify(requestHeadersSpecMock, times(1)).retrieve();
    }

    @Test
    @DisplayName("recover throws ServiceUnavailableException when all attempts fail")
    void recover_ThrowsServiceUnavailableException_WhenAllAttemptsFail() {
        String url = "http://example.com/veiculos?key=55ad1cd0&placa=ABC1234";
        RestClientException exception = new RestClientException("Serviço indisponível.");

        try {
            veiculoDataGatewayImpl.recover(exception, url);
        } catch (Exception e) {
            assertEquals(ServiceUnavailableException.class, e.getClass());
            assertEquals(
                    "Todas as tentativas para o recurso: " + url + "falharam. O serviço está indisponível no momento.",
                    e.getMessage());
        }
    }

}