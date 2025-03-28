package com.example.myapplication;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.myapplication.data.repository.DataRepository;
import com.example.myapplication.presentation.contracts.MainContract;
import com.example.myapplication.presentation.presenter.MainPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {
    @Mock
        private MainContract.View mockView;

    @Mock
    private DataRepository mockRepository;

    private MainPresenter presenter;

    @Before
    public void setUp() {
        presenter = new MainPresenter(mockView, mockRepository);
    }

    @Test
    public void testLoadData_Success() {
        when(mockRepository.getData()).thenReturn("Test Data");

        presenter.loadData();

        verify(mockView).showData("Test Data");
    }

    @Test
    public void testLoadData_Error() {
        when(mockRepository.getData()).thenReturn(null);

        presenter.loadData();

        verify(mockView).showError("Error loading data");
    }
}
