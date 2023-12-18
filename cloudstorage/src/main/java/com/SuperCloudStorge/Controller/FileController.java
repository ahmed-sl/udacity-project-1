package com.SuperCloudStorge.Controller;

import com.SuperCloudStorge.Model.FileModel;
import com.SuperCloudStorge.Services.FileService;
import com.SuperCloudStorge.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping({"/file"})
public class FileController {

    private final FileService fileService;
    private final UserService userService;

    @PostMapping()
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file , RedirectAttributes redirectAttributes) throws IOException {
        boolean isFileUploaded = fileService.uploadFile(file , userService.getUserId());
        redirectAttributes.addFlashAttribute("showAlertMassageFile" , true);
        if(isFileUploaded){
            redirectAttributes.addFlashAttribute("alertMassageFile" , " File Was Uploaded Successfully");
        } else{
            redirectAttributes.addFlashAttribute("alertMassageFile" , " A file with the Same name has been uploaded Already");
        }

        return "redirect:/home";
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFileById(@PathVariable Integer fileId , RedirectAttributes redirectAttributes){
        fileService.deleteFileById(fileId);
        redirectAttributes.addFlashAttribute("showAlertMassageFile" , true);
        redirectAttributes.addFlashAttribute("alertMassageFile" , " File Was Deleted Successfully");
        return "redirect:/home";
    }

    @GetMapping("/view/{fileId}")
    public ResponseEntity<byte[]> viewFile(@PathVariable Integer fileId , RedirectAttributes redirectAttributes) {
        FileModel file = fileService.getFileById(fileId);
        byte[] fileData = file.getFiledata();
        String contentType = file.getContenttype();
        String fileName = file.getFilename(); // Assume the FileModel has a getFileName() method

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(contentType));
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename(fileName)
                .build());


        return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
    }


}
