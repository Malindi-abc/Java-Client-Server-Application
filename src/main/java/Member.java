/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author malin
 */
public class Member {

    public static void main(String[] args) {
       
    }
    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

    private int memberNumber;
    private String memberFirstName;
    private String memberLastName;
    private String memberAddress;
    private int phoneNumber;

    // constructor 
    public Member(int memberNumber, String memberFirstName, String memberLastName, String memberAddress, int phoneNumber) {
        this.memberNumber = memberNumber;
        this.memberFirstName = memberFirstName;
        this.memberLastName = memberLastName;
        this.memberAddress = memberAddress;
        this.phoneNumber = phoneNumber;
    }

    //setting up getters and setters 
    public int getmemberNumber() {
        return memberNumber;

    }

    public void setmemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getmemberFirstName() {
        return memberFirstName;
    }

    public void setmemberFirstName(String memberFirstName) {
        this.memberFirstName = memberFirstName;
    }

    public String getmemberLastName() {
        return memberLastName;
    }

    public void setmemberLastName(String memberLastName) {
        this.memberLastName = memberLastName;
    }

    public String getmemberAddress() {
        return memberAddress;
    }

    public void setmemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public int getphoneNumber(){
        return phoneNumber;
    }
    
    public void setphoneNumber (int phoneNumber){
        this.phoneNumber = phoneNumber;
    }
 
      // Override toString method for easy printing
    @Override
    public String toString() {
        return "Member{" +
                "memberNumber=" + memberNumber +
                ", memberfirstName='" + memberFirstName + '\'' +
                ", memberLastName='" + memberLastName + '\'' +
                ", memberAddress='" + memberAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
    }



