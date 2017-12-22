package com.xing.repositories.browse.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xing.repositories.browse.model.Repository;
import com.xing.repositories.browse.viewmodel.FetchRepositoryViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class BrowseActivityTest {

    private static final String EXTRA_INFO = "extra_info";
    @Mock
    RepositoryListAdapter repositoryListAdapter;
    @Mock
    Intent mockIntent;
    @Mock
    private View view;
    @Mock
    private Bundle bundle;
    @Mock
    private Repository repository;
    private BrowseActivity activity;

    @Before
    public void setUp() {

        activity = Robolectric.setupActivity(BrowseActivity.class);
        MockitoAnnotations.initMocks(this);
        when(mockIntent.getExtras()).thenReturn(bundle);
        when(view.getContext()).thenReturn(activity);
        when(repositoryListAdapter.getItemAt(anyInt())).thenReturn(repository);
    }

    @After
    public void tearDown() throws Exception {
        activity = null;
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void viewmodelShouldNotBeNull() throws Exception {
        assertNotNull(ViewModelProviders.of(activity).get(FetchRepositoryViewModel.class));
    }
}
