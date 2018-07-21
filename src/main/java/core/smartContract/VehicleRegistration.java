package core.smartContract;

import core.blockchain.Validation;

import java.security.PublicKey;
import java.util.ArrayList;

public class VehicleRegistration {

    private PublicKey currentVehicleOwner;
    private String addressOfCurrentOwner;
    private String vehicleID;
    private String engineNumber;
    private String vehicleClass;
    private String condition;
    private String make;
    private String model;
    private String yearOfManufacture;
    private String dateOfRegistration;
    private String engineCapacity;
    private String fuelType;
    private String statusWhenRegistered;
    private String crType;
    private String typeOfBody;

    public boolean registerVehicle(PublicKey currentVehicleOwner, String addressOfCurrentOwner,
                                   String vehicleID, String engineNumber, String vehicleClass,
                                   String condition, String make, String model, String yearOfManufacture,
                                   String dateOfRegistration, String engineCapacity, String fuelType,
                                   String statusWhenRegistered, String crType, String typeOfBody,
                                   ArrayList<Validation> validation){

        this.currentVehicleOwner = currentVehicleOwner;
        this.addressOfCurrentOwner = addressOfCurrentOwner;
        this.vehicleID = vehicleID;
        this.engineNumber = engineNumber;
        this.vehicleClass = vehicleClass;
        this.condition = condition;
        this.make = make;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.dateOfRegistration = dateOfRegistration;
        this.engineCapacity = engineCapacity;
        this.fuelType = fuelType;
        this.statusWhenRegistered = statusWhenRegistered;
        this.crType = crType;
        this.typeOfBody = typeOfBody;

        return true;
    }

    public boolean checkValidity(){
        return true;
    }
}
