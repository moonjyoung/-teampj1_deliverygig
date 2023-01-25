package com.deliverygig.moonjyoung.api;


    

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.deliverygig.moonjyoung.entity.delivery.PickUpAreaEntity;
import com.deliverygig.moonjyoung.entity.image.FoodImageEntity;
import com.deliverygig.moonjyoung.entity.image.PickUpAreaImageEntity;
import com.deliverygig.moonjyoung.entity.image.StoreImageEntity;
import com.deliverygig.moonjyoung.service.image.FoodImageService;
import com.deliverygig.moonjyoung.service.image.PickUpAreaImageService;
import com.deliverygig.moonjyoung.service.image.StoreImageService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class FileController { 
  @Autowired PickUpAreaImageService pService;
  @Autowired StoreImageService sService;
  @Autowired FoodImageService fService;
 
  @Value ("${file.image.pickuparea}") String pickuparea_img_path;
  @Value("${file.image.store}") String store_img_path;
  @Value("${file.image.food}") String food_img_path;
  
  @PutMapping("/upload/{type}")
  public ResponseEntity<Object> putImageUpload(
      @PathVariable String type, @RequestPart MultipartFile file) 
  {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    System.out.println(file.getOriginalFilename());
    Path folderLocation = null;
    if (type.equals("pickuparea")) {
      folderLocation = Paths.get(pickuparea_img_path);
    }
    else if (type.equals("store")) {
      folderLocation = Paths.get(store_img_path);
    }
    else if (type.equals("food")) {
      folderLocation = Paths.get(food_img_path);
    }
    else {
      resultMap.put("status", false);
      resultMap.put("message", "타입정보가 잘못되었습니다.(ex : /pickuparea/upload, /store/upload , /food/upload");
      return new ResponseEntity<Object>(resultMap, HttpStatus.BAD_REQUEST);
    }
    
    String originFileName = file.getOriginalFilename();
    String[] split = originFileName.split("\\.");
    String ext = split[split.length - 1];
    String filename = "";
    for (int i = 0; i < split.length - 1; i++) {
      filename += split[i]; 
    }
    String saveFilename = type + "_"; 
    Calendar c = Calendar.getInstance();
    saveFilename += c.getTimeInMillis() + "." + ext; 


    
    Path targetFile = folderLocation.resolve(saveFilename); 
    try {
      Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING); 
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (type.equals("pickuparea")) {
        PickUpAreaImageEntity data = new PickUpAreaImageEntity();
      data.setPuaiImage(saveFilename);
      data.setPuaiUri(filename);
      // lService.addLocationImage(data);
      Map<String,Object> resultMap2 = pService.addPickUpAreaImage(data);
    
      return new ResponseEntity<Object>(resultMap2 , (HttpStatus)resultMap2.get("code"));
     
     // resultMap.put("message", "location 성공");
     
    }
    
    else if (type.equals("store")) {
        StoreImageEntity data = new StoreImageEntity();
        data.setSimgImage(saveFilename);
        data.setSimgUri(filename);
        //sService.addStoreImage(data);
        Map<String,Object> resultMap2 = sService.addStoreImage(data);
      return new ResponseEntity<Object>(resultMap2 , (HttpStatus)resultMap2.get("code"));
           
        //map.put("message", "store 성공");
    }
    else if (type.equals("food")) {
      FoodImageEntity data = new FoodImageEntity();
      data.setFiFile(saveFilename);
      data.setFiUri(filename);
      //fService.addFoodImage(data);
      Map<String,Object> resultMap2 = fService.addFoodImage(data);
      return new ResponseEntity<Object>(resultMap2 , (HttpStatus)resultMap2.get("code"));
      //map.put("message", "food 성공");
    }
    
    return new ResponseEntity<>(resultMap, HttpStatus.OK);
  }  
}
