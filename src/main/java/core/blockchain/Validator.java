package core.blockchain;

public class Validator {

    private String address;
    private boolean isMandotory;

    public Validator(String address, boolean isMandotory){
        this.address = address;
        this.isMandotory = false;
    }

    public String getAddress() {
        return address;
    }

    public boolean isMandotory() {
        return isMandotory;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMandotory(boolean mandotory) {
        isMandotory = mandotory;
    }
}
