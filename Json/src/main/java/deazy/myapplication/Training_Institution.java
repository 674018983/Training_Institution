package deazy.myapplication;

/**
 * Created by XZC on 2017/4/14.
 */

public class Training_Institution {
    String CompanyName;
    String CompanyAddress;

    public Training_Institution(String companyName, String companyAddress) {
        CompanyName = companyName;
        CompanyAddress = companyAddress;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public String getCompanyAddress() {
        return CompanyAddress;
    }
}
