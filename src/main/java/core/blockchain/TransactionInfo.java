package core.blockchain;

public class TransactionInfo {
    private int threshod;
    private String smartContractId;
    private String smartContractSignature;
    private String smartContractMethod;
    private String[] parameters;
    private String event;
    private String data;



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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

//
//    @Override
//    public String toString(){
//
//        return null;
//    }
}
