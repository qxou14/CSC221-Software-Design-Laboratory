package sample;

import java.sql.*;

public class ConnectData {
    private Connection con;
    private static String username = "root";
    private static String password = "Aa1031354803.";
    private int Finalcount;
    String[] storage; //array depends on number of students


    public ConnectData() {
        con = null;
        try {
            //import connector jar to module first before doing anything
            Class.forName("com.mysql.cj.jdbc.Driver");
            // most people will be "jdbc:mysql://localhost:3306/student  however something wrong with my timezone
            // so ?serverTimezone=UTC is added
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?serverTimezone=UTC", username, password);

            if (con != null) {
                System.out.println("I have connected to the server!");
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException:	" + ex.getMessage());
        } catch (SQLException sq) {
            System.out.println("SQLException: " + sq.getMessage());
        }
        createTables();
        Insert();
        showvalue();
        showCourse();
        showStudent();
        forPieChart();

    }


    public void createTables() {
        try {//primary key cannot be null
            // we might encouter an error called Students already exist, so we add if not exists then create
            PreparedStatement studentTable = con.prepareStatement("CREATE TABLE IF NOT EXISTS Students"
                    + "("
                    + "studentID INT UNSIGNED not NULL AUTO_INCREMENT,"
                    + "firstName VARCHAR(255),"
                    + "lastName VARCHAR(255),"
                    + "email VARCHAR(255),"
                    + "sex VARCHAR(255),"
                    + "PRIMARY KEY(studentID)"
                    + ")");

            studentTable.execute();

            PreparedStatement courseTable = con.prepareStatement("CREATE TABLE IF NOT EXISTS Courses"
                    + "("
                    + "courseID INT UNSIGNED not NULL,"
                    + "courseTitle VARCHAR(255),"
                    + "Department VARCHAR(255),"
                    + "PRIMARY KEY(courseID)"
                    + ")"
            );

            courseTable.execute();


            PreparedStatement classTable = con.prepareStatement("CREATE TABLE IF NOT EXISTS classes"
                    + "("
                    + "courseID INT UNSIGNED not NULL,"
                    + "studentID INT UNSIGNED not NULL,"
                    + "section INT UNSIGNED not NULL,"
                    + "year INTEGER,"
                    + "semester VARCHAR(255),"
                    + "GPA VARCHAR(255),"
                    + "PRIMARY KEY(section,studentID,courseID),"
                    + "FOREIGN KEY(studentID) REFERENCES Students(studentID),"
                    + "FOREIGN KEY(courseID) REFERENCES Courses(courseID)"
                    + ")");

            classTable.execute();

        }
        //add 1 after exception because there is also another exception
        catch (Exception ex) {
            System.out.println("Exception1: " + ex.getMessage());
        }


    }

    public void Insert() {
        try {


            drop();
            PreparedStatement resetPrimary = con.prepareStatement("ALTER TABLE students AUTO_INCREMENT = 1");
            resetPrimary.execute(); //reset the ID or else ID will keep increment nonstoping even the people are the same.

            PreparedStatement insertStudent = con.prepareStatement("INSERT INTO students(firstName,lastName,email,sex)"
                    + "VALUES('Jason','Jones','jasonjones@gmail.com','M'),"
                    + "('Ricky','Lee','rick@yahoo.com','M'),"
                    + "('Lucy','Smith','lucy@gmail.com','F'),"
                    + "('Adam','Walker','walker@yahoo.com','M'),"
                    + "('Eva','Hill','eva@gmail.com','F'),"
                    + "('Michael','Chen','Michael@gmail.com','M'),"
                    + "('William','Miller','william@gmail.com','M'),"
                    + "('Blue','Smith','blue@yahoo.com','M'),"
                    + "('Stephanie','Brown','phanie@gmail.com','F'),"
                    + "('Nancy','Lin','lin@gmail.com','F'),"
                    + "('Jojo','Wilson','Jojo@gmail.com','F'),"
                    + "('Amy','Grey','amy@gmail.com','F'),"
                    + "('Mike','Chen','Mike@gmail.com','M'),"
                    + "('Ryan','Wu','ryan@gmail.com','M'),"
                    + "('Sophie','Gracia','sophie@gmail.com','F'),"
                    + "('Steven','Li','Ste@gmail.com','M'),"
                    + "('Ken','Kim','Ken@gmail.com','M'),"
                    + "('Eric','Li','Eric@gmail.com','M'),"
                    + "('Serena','Smith','serna@gmail.com','F'),"
                    + "('Emily','Li','emily@gmail.com','F'),"
                    + "('Susan','Park','susan@gmail.com','F'),"
                    + "('Alex','Yu','alex@yahoo.com','M'),"
                    + "('Cato','Hu','cato@gmail.com','M'),"
                    + "('Nathan','Lee','nathanlee@gmail.com','M'),"
                    + "('Jonathan','Cai','jona@gmail.com','M')");

            insertStudent.execute();

            PreparedStatement insertCourses = con.prepareStatement("INSERT INTO courses(courseID, courseTitle,department)"
                    + "VALUES(22100,'Software Design Laboratory','Computer Science'),"
                    + "(20100,'Calculus 1','Mathematics'),"
                    + "(20700,'Physics','Science'),"
                    + "(11100,'Foundation of Speech Communication','Speech')");
            insertCourses.execute();

            PreparedStatement insertClasses = con.prepareStatement("INSERT INTO classes(courseID,studentID,section,year,semester,GPA)"
                    + "VALUES(20100,1,100,2020,'Spring','A'),"
                    + "(22100,2,101,2020,'Spring','A'),"
                    + "(22100,3,101,2020,'Spring','B'),"
                    + "(20700,4,103,2020,'Spring','C'),"
                    + "(20700,5,103,2020,'Spring','B'),"
                    + "(11100,6,102,2020,'Spring','A'),"
                    + "(22100,7,101,2020,'Spring','B'),"
                    + "(22100,8,101,2020,'Spring','D'),"
                    + "(22100,9,101,2020,'Spring','A'),"
                    + "(22100,10,101,2020,'Spring','F'),"
                    + "(22100,11,101,2020,'Spring','C'),"
                    + "(20700,12,103,2020,'Spring','A'),"
                    + "(22100,13,101,2020,'Spring','A'),"
                    + "(22100,14,101,2020,'Spring','F'),"
                    + "(22100,15,101,2020,'Spring','W'),"
                    + "(20100,16,100,2020,'Spring','B'),"
                    + "(22100,17,101,2020,'Spring','C'),"
                    + "(22100,18,101,2020,'Spring','F'),"
                    + "(22100,19,101,2020,'Spring','B'),"
                    + "(22100,20,101,2020,'Spring','C'),"
                    + "(11100,21,102,2020,'Spring','B'),"
                    + "(22100,22,101,2020,'Spring','C'),"
                    + "(22100,23,101,2020,'Spring','A'),"
                    + "(22100,24,101,2020,'Spring','B'),"
                    + "(22100,25,101,2020,'Spring','A')");

            insertClasses.execute();

        } catch (Exception ex) {
            System.out.println("Exception2:" + ex.getMessage());
        }
    }

    public void drop() {
        try {
            PreparedStatement student = con.prepareStatement("DELETE FROM students");
            PreparedStatement course = con.prepareStatement("DELETE FROM courses");
            PreparedStatement classes = con.prepareStatement("DELETE FROM classes");
            classes.execute();
            student.execute();
            course.execute();


        } catch (Exception ex) {
            System.out.println("DropException:" + ex.getMessage());
        }

    }

    //Listing 25.2 I combine two methods and simply it
    public void showvalue() {
        String print;
        int count = 0;

        try {
            Statement show = con.createStatement();
            ResultSet result = show.executeQuery("SELECT * FROM student.classes WHERE section = 101");
            ResultSetMetaData r = result.getMetaData();
            int numColumns = r.getColumnCount();

            System.out.println("courseID  studentID   section   Year   Semester    GPA");
            while (result.next()) {
                print = "";
                for (int i = 1; i <= numColumns; i++) {

                    String temp = result.getString(i);
                    if (temp != null) {
                        print += temp + "-------";
                        count++;

                    }

                }

                System.out.println(print);

            }
            Finalcount = count / numColumns;
            storage = new String[Finalcount];

            //count = number of rows * number of columns , number of students= number of rows.
            System.out.println("There are total of " + Finalcount + " students who enroll in Csc22100 in spring semester!");
        } catch (Exception ex) {
            System.out.println("ShowValueExcpetion: " + ex.getMessage());
        }

    }

    public void showStudent() //show value of student table
    {
        String print;


        try {
            Statement show = con.createStatement();
            ResultSet result = show.executeQuery("SELECT * FROM student.students ");
            ResultSetMetaData r = result.getMetaData();
            int numColumns = r.getColumnCount();

            System.out.println("studentID  firstName lastName         email             sex");
            while (result.next()) {
                print = "";
                for (int i = 1; i <= numColumns; i++) {

                    String temp = result.getString(i);
                    if (temp != null) {
                        print += temp + "--------";

                    }

                }

                System.out.println(print);

            }
        } catch (Exception ex) {
            System.out.println("ShowValueExcpetion: " + ex.getMessage());
        }
    }

    public void showCourse() //this show value for course table
    {

        String print;


        try {
            Statement show = con.createStatement();
            ResultSet result = show.executeQuery("SELECT * FROM student.courses ");
            ResultSetMetaData r = result.getMetaData();
            int numColumns = r.getColumnCount();

            System.out.println("");
            while (result.next()) {
                print = "";
                for (int i = 1; i <= numColumns; i++) {

                    String temp = result.getString(i);
                    if (temp != null) {
                        print += temp + "-------";

                    }

                }

                System.out.println(print);

            }
        } catch (Exception ex) {
            System.out.println("ShowValueExcpetion: " + ex.getMessage());
        }
    }

    //this is for piechart use only! Exactly same as above method, other than change of condition
    public void forPieChart() {
        int j = 0;
        String print;
        try {
            Statement show = con.createStatement();
            ResultSet result = show.executeQuery("SELECT GPA FROM student.classes WHERE section = 101");
            ResultSetMetaData r = result.getMetaData();
            int numColumns = r.getColumnCount();

            System.out.println("GPA");
            while (result.next()) {
                print = "";
                for (int i = 1; i <= numColumns; i++) {
                    String temp = result.getString(i);

                    if (temp != null) {
                        print += temp;
                    }

                }

                System.out.println(print);
                storage[j] = print;
                j++;
            }

        } catch (Exception ex) {
            System.out.println("ShowValueExcpetion: " + ex.getMessage());
        }

    }


    //for my use only
    public int getNumberOfStudent() {
        return Finalcount;
    }


    public void getArray() {

        for (int i = 0; i < storage.length; i++) {
            System.out.println(storage[i]);
        }
    }

}