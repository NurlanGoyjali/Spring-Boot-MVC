package com.example.hiperecommerce.service;

import com.example.hiperecommerce.reposiory.RoleRepository;
import com.example.hiperecommerce.reposiory.UserRepositry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RoleServiceTest {

    private RoleRepository roleRepository;
    private  UserService userService;
    private UserRepositry userRepositry;
    private RoleService roleService;

    @Before
    public void setUp() throws Exception {
        roleRepository = Mockito.mock(RoleRepository.class);
        userService = Mockito.mock(UserService.class);
        userRepositry = Mockito.mock(UserRepositry.class);
        roleService = new RoleService(roleRepository,userService);
    }
    @Test
    public void whenGetroles(){



        Mockito.when(roleService.getRoles(4L)).thenReturn(new ArrayList<>());
    }
}