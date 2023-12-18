package com.SuperCloudStorge.Services;

import com.SuperCloudStorge.Mapper.CredentialsMapper;
import com.SuperCloudStorge.Model.CredentialModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CredentialService {

    private final UserService userService;
    private final CredentialsMapper credentialsMapper;
    private final EncryptionService encryptionService;


    public Boolean isCredentialExist(Integer credential_id , Integer userId){return credentialsMapper.credential_id(credential_id , userId) != 0;}


    public List<CredentialModel> getUserCredentia(Integer currentUserId){
        return credentialsMapper.getUserCredentialByUserId(currentUserId);
    }



    public Boolean createCredential(CredentialModel credential_Model){

        boolean  isCredentialExistInDatabase = isCredentialExist(credential_Model.getCredentialid(),userService.getUserId());
        if(isCredentialExistInDatabase){
            String encryptedPassword = encryptionService.encryptValue(credential_Model.getPassword() , credential_Model.getKey());
            credential_Model.setEncryptedPassword(encryptedPassword);
            credential_Model.setUserid(userService.getUserId());
            credentialsMapper.update(credential_Model);
            return false;
        }
            credential_Model.setUserid(userService.getUserId());
            credential_Model.setKey(getRandomKey());
            String encryptedPassword = encryptionService.encryptValue(credential_Model.getPassword() , credential_Model.getKey());
            credential_Model.setEncryptedPassword(encryptedPassword);
            credentialsMapper.insert(credential_Model);
            return true;

    }

    public void deleteCredentialById(Integer credential_id){
        credentialsMapper.deleteCredentialById(credential_id);
    }


    public String getRandomKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }
}
