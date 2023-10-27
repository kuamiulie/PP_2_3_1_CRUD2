package PP231.model;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "name cannot be empty")
    @Pattern(regexp = "[a-zA-Z]+", message = "Name can consist of only letters")
    private String name;

    @NotEmpty(message = "Name of your country cannot be empty")
    @Pattern(regexp = "[a-zA-Z]+", message = "Country name can consist of only letters")
    private String countryOfBirth;

    public User() {
    }

    public User(String name, String countryOfBirth) {
        this.name = name;
        this.countryOfBirth = countryOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryOfBirth='" + countryOfBirth + '\'' +
                '}';
    }
}
