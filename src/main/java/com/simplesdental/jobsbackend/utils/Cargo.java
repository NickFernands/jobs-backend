package com.simplesdental.jobsbackend.utils;

public enum Cargo {
    Desenvolvedor(0),
    Designer(1),
    Suporte(2),
    Tester(3);


    public int cargoValue;

    Cargo(int value) {
        cargoValue = value;
    }

    public int getCargoValue() {
        return cargoValue;
    }

}
