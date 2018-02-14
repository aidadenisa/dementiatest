package dt.model;

import dt.interfaces.User;

import java.util.Date;
import java.util.List;

public class Doctor implements User {

    private int id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String email;
    private String address;

    private List<Patient> patients;

}
