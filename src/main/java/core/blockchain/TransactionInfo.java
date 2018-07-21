package core.blockchain;

public class TransactionInfo {
    private int threshod;
    private String smartContractId;
    private String smartContractSignature;
    private String smartContractMethod;
    private String[] parameters;

    public int getThreshod() {
        return threshod;
    }

    public String getSmartContractId() {
        return smartContractId;
    }

    public String getSmartContractSignature() {
        return smartContractSignature;
    }

    public String getSmartContractMethod() {
        return smartContractMethod;
    }

    public String[] getParameters() {
        return parameters;
    }

    public void setThreshod(int threshod) {
        this.threshod = threshod;
    }

    public void setSmartContractId(String smartContractId) {
        this.smartContractId = smartContractId;
    }

    public void setSmartContractSignature(String smartContractSignature) {
        this.smartContractSignature = smartContractSignature;
    }

    public void setSmartContractMethod(String smartContractMethod) {
        this.smartContractMethod = smartContractMethod;
    }

    public void setParameters(String[] parameters) {
        this.parameters = parameters;
    }

//
//    @Override
//    public String toString(){
//
//        return null;
//    }
}
