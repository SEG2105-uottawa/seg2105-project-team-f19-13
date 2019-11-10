package com.example.clinicapp;

public class Service {
    public String serviceName, serviceRole, id;

    public Service(){

    }

    public Service(String id, String serviceName, String serviceRole)
    {
       this.id = id;
        this.serviceName = serviceName;
        this.serviceRole = serviceRole;
    }

}
