
import java.io.Serializable;

/**
 * Represents a member of the fitness club.
 */
public class Member implements Serializable {

    private int memberNumber;
    private String memberFirstName;
    private String memberLastName;
    private String memberAddress;
    private String phoneNumber;

    // Constructor for member class 
    public Member(int memberNumber, String memberFirstName, String memberLastName, String memberAddress, String phoneNumber) {
        this.memberNumber = memberNumber;
        this.memberFirstName = memberFirstName;
        this.memberLastName = memberLastName;
        this.memberAddress = memberAddress;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters to get and set member details
    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getMemberFirstName() {
        return memberFirstName;
    }

    public void setMemberFirstName(String memberFirstName) {
        this.memberFirstName = memberFirstName;
    }

    public String getMemberLastName() {
        return memberLastName;
    }

    public void setMemberLastName(String memberLastName) {
        this.memberLastName = memberLastName;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Override toString
    @Override
    public String toString() {
        return "Member{"
                + "memberNumber=" + memberNumber
                + ", memberFirstName='" + memberFirstName + '\''
                + ", memberLastName='" + memberLastName + '\''
                + ", memberAddress='" + memberAddress + '\''
                + ", phoneNumber='" + phoneNumber + '\''
                + '}';
    }
}
