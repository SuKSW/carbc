package core.blockchain;

import java.security.PublicKey;

public class Validator {

    private PublicKey validator;
    private boolean isMandotory;
    private String role;
    private int priority;

    public Validator(PublicKey validator, String role, boolean isMandotory, int priority){
        this.validator = validator;
        this.role = role;
        this.isMandotory = true;
        this.priority = priority;
    }

    public PublicKey getValidator() {
        return validator;
    }

    public boolean isMandotory() {
        return isMandotory;
    }

    public String getRole() {
        return role;
    }

    public int getPriority() {
        return priority;
    }


    public void setValidator(PublicKey validator) {
        this.validator = validator;
    }

    public void setMandotory(boolean mandotory) {
        isMandotory = mandotory;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
