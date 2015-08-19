/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bartersystem;

import java.io.File;

/**
 *
 * @author Sumeet
 */
public class ProductInfo {
    
    private String name,type,description,expectation;

    private File imageFile;

    
    private int user_id,quantity;
    
    public ProductInfo(int user_id, String name, String type, int quantity, String description,String expectation,File imageFile){
        
    this.user_id = user_id;
    this.name = name;
    this.type = type;
    this.quantity = quantity;
    this.description = description;
    this.expectation = expectation;
    this.imageFile=imageFile;
    
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getExpectation() {
        return expectation;
    }

    public void setExpectation(String expectation) {
        this.expectation = expectation;
    }
    
    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }
    
    
}
