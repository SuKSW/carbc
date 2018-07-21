import chainUtil.ChainUtil;
import core.blockchain.TransactionInfo;
import core.blockchain.TransactionProposal;
import core.blockchain.Validator;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Calendar;

public class TransactionProposalTest {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, SignatureException, NoSuchProviderException, InvalidKeyException, InvalidKeySpecException {
        Validator validator1 = new Validator("v1","owner",true,3);
        Validator validator2 = new Validator("v2","seller",true,4);
        ArrayList<Validator> validators = new ArrayList<>();
        validators.add(validator1);
        validators.add(validator2);
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());

        TransactionInfo transactionInfo = new TransactionInfo();
        TransactionProposal proposal = new TransactionProposal("sender",validators,
                "data","proposal-1",currentTimestamp,transactionInfo);

        proposal.isValid(); 
    }
}
