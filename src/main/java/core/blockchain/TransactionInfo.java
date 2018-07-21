package core.blockchain;

public class TransactionInfo {
    private int threshod;
    private int smartContractId;
    private byte[] smartContractSignature;
    private String smartContractMethod;
    private String parameters;

    public int getThreshod() {
        return threshod;
    }

    public int getSmartContractId() {
        return smartContractId;
    }

    public byte[] getSmartContractSignature() {
        return smartContractSignature;
    }

    public String getSmartContractMethod() {
        return smartContractMethod;
    }

    public String getParameters() {
        return parameters;
    }

    public void setThreshod(int threshod) {
        this.threshod = threshod;
    }

    public void setSmartContractId(int smartContractId) {
        this.smartContractId = smartContractId;
    }

    public void setSmartContractSignature(byte[] smartContractSignature) {
        this.smartContractSignature = smartContractSignature;
    }

    public void setSmartContractMethod(String smartContractMethod) {
        this.smartContractMethod = smartContractMethod;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }


    @Override
    public String toString(){

        return null;
    }
}
