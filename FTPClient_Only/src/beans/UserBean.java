/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author hp1
 */
public class UserBean {
    private int userid ;
    private String username;
    private String password,usertype,name,contact,dob,email,address;
    private boolean userstatus;

    public int getUserId() {
        return userid;
    }
    public void setUserId(int userid) {
        this.userid = userid;
    }
    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(String usertype) {
        this.usertype = usertype;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setEmailId(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUserStatus(boolean userstatus) {
        this.userstatus = userstatus;
    }

    public String getUserType() {
        return usertype;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getDob() {
        return dob;
    }

    public String getEmailId() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public boolean isUserStatus() {
        return userstatus;
    }
}
