package com.geekykel;

import java.util.List;
import com.geekykel.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class QueryStudent {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			// start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			// query students: lastName='Doe'
			theStudents = session.createQuery("from Student s where s.lastName='John'").getResultList();
			
			// display the students
			System.out.println("\n\nStudents who have last name of John");
			displayStudents(theStudents);
			
			// query students: lastName='Doe' OR firstName='Daffy'
			theStudents =
					session.createQuery("from Student s where"
							+ " s.lastName='John' OR s.firstName='Daffy'").getResultList();
			
			System.out.println("\n\nStudents who have last name of John OR first name Daffy");
			displayStudents(theStudents);
			
			// query students where email LIKE '%gmail.com'
			theStudents = session.createQuery("from Student s where"
					+ " s.email LIKE '%gmail.com'").getResultList();

			System.out.println("\n\nStudents whose email ends with gmail.com");			
			displayStudents(theStudents);
			
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			session.close();

			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}





