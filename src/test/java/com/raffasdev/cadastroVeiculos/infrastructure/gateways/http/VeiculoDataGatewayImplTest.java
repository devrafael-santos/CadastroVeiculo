package com.raffasdev.cadastroVeiculos.infrastructure.gateways.http;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VeiculoDataGatewayImplTest {

    @Mock
    private RestClient restClientMock;

    @InjectMocks
    private VeiculoDataGatewayImpl veiculoDataGatewayImpl;

    @BeforeEach
    void setUp() {
        var requestHeadersUriSpecMock = mock(RestClient.RequestHeadersUriSpec.class);
        var requestHeadersSpecMock = mock(RestClient.RequestHeadersSpec.class);
        var responseSpecMock = mock(RestClient.ResponseSpec.class);

        var mockResponse = VeiculoCreator.createValidVeiculoDataGatewayImplResponse();

        when(restClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.body(VeiculoDataGatewayResponse.class)).thenReturn(mockResponse);

        veiculoDataGatewayImpl = new VeiculoDataGatewayImpl(restClientMock);
    }


    @Test
    @DisplayName("getVeiculoInfosByPlaca returns VeiculoDataGatewayResponse when successful")
    void getVeiculoInfosByPlaca_ReturnsVeiculoDataGatewayResponse_WhenSuccessful() {

        var response = veiculoDataGatewayImpl.getVeiculoInfosByPlaca("ABC1234");

        verify(restClientMock, times(1)).get();
        assertEquals(VeiculoCreator.createValidVeiculoDataGatewayImplResponse(), response);
    }

}