package com.example.mechtech;

public class ServiceStations {
    String Code, Name, County, Address, WorkingHours;

    public ServiceStations() {
    }

    public ServiceStations(String code, String name, String county, String address, String workingHours) {
        Code = code;
        Name = name;
        County = county;
        Address = address;
        WorkingHours = workingHours;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCounty() {
        return County;
    }

    public void setCounty(String county) {
        County = county;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getWorkingHours() {
        return WorkingHours;
    }

    public void setWorkingHours(String workingHours) {
        WorkingHours = workingHours;
    }
}
