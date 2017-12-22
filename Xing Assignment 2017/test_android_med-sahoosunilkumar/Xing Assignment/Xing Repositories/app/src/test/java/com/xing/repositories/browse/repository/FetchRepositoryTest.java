package com.xing.repositories.browse.repository;

import com.xing.repositories.browse.model.Repository;
import com.xing.repositories.networkinterface.NetworkApiService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FetchRepositoryTest {
    @InjectMocks
    private FetchRepository subject;
    @Mock
    private NetworkApiService networkApiService;
    @Mock
    private IFetchRepositoryCallback callback;
    @Mock
    private Response<List<Repository>> fetchItemsResponseResponse;
    @Mock
    private Call<List<Repository>> fetchItemsResponseCall;

    @Before
    public void setUp() throws Exception {
        subject = new FetchRepository(callback);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadRange() throws Exception {
        when(networkApiService.getUser(anyInt(), anyInt())).thenReturn(fetchItemsResponseCall);
        when(fetchItemsResponseCall.execute()).thenReturn(fetchItemsResponseResponse);
        subject.loadRange(1, 20);
        then(networkApiService).should(times(1)).getUser(anyInt(), anyInt());
        then(callback).should(times(2)).onStateChanged(anyInt());
    }
}
