package com.example.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Student {
 private int id = 1;
 private String name = "Nirmala";
 private String gender = "Female";

 @Autowired // 4. injecting the Certification object.
 private Certification certification;
 @Override
 public String toString() {
 return "Student [id=" + id +
 ", name=" + name +
 ", gender=" + gender +
 ", certification=" + certification + "]";
 }
 public void display() {
 System.out.println("Student ID: " + id);
 System.out.println("Name: " + name);
 System.out.println("Gender: " + gender);
 System.out.println(certification);
 }
}
