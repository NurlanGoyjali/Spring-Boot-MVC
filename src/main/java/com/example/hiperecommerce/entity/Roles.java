package com.example.hiperecommerce.entity;

public enum Roles {
    USER{
        private  long Id;
        private String name = "USER";
    },
    MANAGER{
        private  long Id;
        private String name = "MANAGER";
    },
    ADMIN{
        private  long Id;
        private String name ="ADMIN" ;
    },
    DELIVER{
        private  long Id;
        private String name = "DELIVER";
    },
    SELLER{
        private  long Id;
        private String name = "SELLER";
    }

}
