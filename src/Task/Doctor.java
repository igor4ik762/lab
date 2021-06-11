package Task;

import java.io.Serializable;
import java.util.Scanner;

public abstract class Doctor implements Serializable {
    String surname;       //прізвище
    String profession;    //спеціальність

    //конструктор без параметрів
    public Doctor() {
        surname = "";
        profession = "";
    }

    //конструктор з параметрами
    public Doctor(String surname, String profession) {
        this.surname = surname;
        this.profession = profession;
    }

    //метод для вводу даних
    public abstract void input();

    //метод для виводу даних
    public abstract void output();

    //перевизначення методу toString()
    @Override
    public String toString() {
        return "Doctor{" +
                "surname='" + surname + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }

    //гетери
    public String getSurname() {return surname;}
    public String getProfession() {return profession;}

    //сетери
    public void setSurname(String surname) {this.surname = surname;}
    public void setProfession(String profession) {this.profession = profession;}
}
