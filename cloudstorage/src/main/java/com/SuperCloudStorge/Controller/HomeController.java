package com.SuperCloudStorge.Controller;

import com.SuperCloudStorge.Model.CredentialModel;
import com.SuperCloudStorge.Model.FileModel;
import com.SuperCloudStorge.Model.NoteModel;
import com.SuperCloudStorge.Services.CredentialService;
import com.SuperCloudStorge.Services.FileService;
import com.SuperCloudStorge.Services.NoteService;
import com.SuperCloudStorge.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
     private final FileService fileService;
     private final NoteService noteService;
     private final UserService userService;
     private final CredentialService credentialService;


    @GetMapping
    public String signupPage(Model model) {
        Integer currentUserId = userService.getUserId();

        List<CredentialModel> userCredentials =  credentialService.getUserCredentia(currentUserId);
        List<FileModel> userFiles = fileService.getUserFile(currentUserId);
        List<NoteModel> userNotes = noteService.getUserNote(currentUserId);

        model.addAttribute("files" ,userFiles);
        model.addAttribute("notes" ,userNotes);
        model.addAttribute("credentials" ,userCredentials);

        return "home";
    }
}

