package dt.model;

import dt.interfaces.User;

import java.util.Date;
import java.util.List;

public class Patient implements User {

    private int id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String email;
    private String address;

    private List<Test> takenTests;

    private List<Doctor> doctors;

}
