package com.SuperCloudStorge.Controller;


import com.SuperCloudStorge.Model.CredentialModel;
import com.SuperCloudStorge.Services.CredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping({"/credential"})

public class CredentialController {

      private final CredentialService credentialService;
    @PostMapping
    public String createCredential(CredentialModel credentialModel, RedirectAttributes redirectAttributes){
        boolean credentialCreated = credentialService.createCredential(credentialModel);
        redirectAttributes.addFlashAttribute("showAlertMassageCredential" , true);
        if(credentialCreated){
            redirectAttributes.addFlashAttribute("alertMassageCredential" , " Credential Was Created Successfully");
        }else {
            redirectAttributes.addFlashAttribute("alertMassageCredential" , " Credential Was Updated Successfully");
        }

        return "redirect:/home";
    }

    @GetMapping("delete/{credentialid}")
    public String deleteCredential(@PathVariable Integer credentialid , RedirectAttributes redirectAttributes){
        credentialService.deleteCredentialById(credentialid);
        redirectAttributes.addFlashAttribute("showAlertMassageCredential" , true);
        redirectAttributes.addFlashAttribute("alertMassageCredential" , " Credential Was Deleted Successfully");
        return "redirect:/home";
    }


}
