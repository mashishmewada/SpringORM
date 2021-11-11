package com.spring.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;
import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		StudentDao studentDao = context.getBean("studentDao", StudentDao.class);

//       Student student = new Student(124,"Shubham Mewada","Sehore");
//       int r = studentDao.insert(student);
//       System.out.println("Done.. "+r);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("********** Welcome to my ORM Project **********");

		boolean go = true;
		while (go) {

			System.out.println("PRESS 1 for new student");
			System.out.println("PRESS 2 for get details of single student");
			System.out.println("PRESS 3 for display all student");
			System.out.println("PRESS 4 for delete student");
			System.out.println("PRESS 5 for update student");
			System.out.println("PRESS 6 for Exit");

			try {

				int input = Integer.parseInt(br.readLine());

//        	   if (input == 1) 
//        	   {
//				//add a new student
//			} else if (input == 2) 
//			{
//				//display all student
//			}
				switch (input) {
				case 1:
					// add a new student
					// taking inputs from user
					System.out.println("Please provide student details...");

					System.out.println("Student Id: ");
					String a = br.readLine();
					// or
					/*
					 * int a =Integer.parseInt(br.readline());
					 * 
					 */

					System.out.println("Student Name: ");
					String b = br.readLine();

					System.out.println("Student City: ");
					String c = br.readLine();

					// creating student object and setting values
					Student student = new Student(Integer.parseInt(a), b, c);
					// or
					/*
					 * Student student = new Student); student.setStudentId(a);
					 * student.setStudentName(b); student.setStudentCity(c);
					 * 
					 */

					// saving student object to database by calling insert of student dao
					int r = studentDao.insert(student);

					System.out.println("------------------------------------------------");
					System.out.println("Submitted.... ");
					System.out.println("ID: " + r);
					System.out.println("------------------------------------------------");
					break;

				case 2:
					// get details of single student
					System.out.println("Enter Student ID:");
					String studentId = br.readLine();

					Student s = studentDao.getStudent(Integer.parseInt(studentId));

					System.out.println("***************************************************");
					System.out.println("ID " + s.getStudentId() + ", Name: " + s.getStudentName() + ", City: "
							+ s.getStudentCity());
//					System.out.println(s.getStudentName());
//					System.out.println(s.getStudentCity());
					System.out.println("------------------------------------------------");

					break;

				case 3:
					// display all student
					System.out.println("***************************************************");

					// get all students from the studentDao findAllStudents() method and store
					List<Student> students = studentDao.findAllStudents();
					for (Student st : students) {
						System.out.print("\n");
						System.out.println("ID " + st.getStudentId() + ", Name: " + st.getStudentName() + ", City: "
								+ st.getStudentCity());
						System.out.print("-----------------------------------------------");
					}
					System.out.println("\n");

					break;

				case 4:
					// delete student
					System.out.println("***************************************************");

					System.out.println("Enter Student ID:");
					String studentId1 = br.readLine();
					Student s1 = studentDao.deleteStudent(Integer.parseInt(studentId1));
					System.out.println("\n");
					System.out.println("Deleted successfully");

//			        //int status = studentDao.deleteStudent(s1.getStudentId());
//			        //use this statement for wrong studentId, empty string or null
//			        if(studentId1 == null || studentId1 == "") 
//			        {
//			        	System.out.println("ERROR while deleting");
//			        }
//			        else
//			        {
//			        	System.out.println("Deleted successfully");
//			        }

					System.out.println("------------------------------------------------");
					System.out.println("\n");

					break;

				case 5:
					// update student
					System.out.println("***************************************************");

					System.out.println("Enter Student ID:");
					String stId = br.readLine();
					System.out.println("Enter New Student Name:");
					String stName = br.readLine();
					System.out.println("Enter New Student City:");
					String stCity = br.readLine();
					// after user enters values, store them in a student2 variable
					Student student1 = new Student(Integer.parseInt(stId), stName, stCity);
					studentDao.updateStudent(student1);
					System.out.println("\n");
					System.out.println("Data updated successfully");
					System.out.println("------------------------------------------------");
					// show this data
					break;

				case 6:
					// exit
					System.out.println("*******************THANK YOU********************");
					go = false;
					break;

				default:
					System.out.println("Please Press a valid key !! \n");
				}

			} catch (Exception e) {
				System.out.println("Invalid Input Try with another one !!");
				System.out.println(e.getMessage());
				System.out.println("\n");
			}
		}
		System.out.println("Have a nice day !!");

	}
}
