#Budget Application

##Overview
This project allows users to track their spending on various categories in their budgets. The total expenditures of the user is always displayed to show how much money is currently being used from the budget, and spending distribution can be viewed as a table. Please note that this project is still being updated. Graphs will be added in order to better portray one's budget and the proportions being spent in each category.

This application was created for the purposes of practicing web development by Eric Cha, a current computer science major studying at the University of Maryland, Baltimore County. The project used the Spring MVC framework, and is a largely Java and Javascript based. Spring Security was used to differentiate between users and admins. User data as well as data for budgets was stored with queries to MySQL by using JDBC. Lastly, configuration with Microsoft Outlook was necessary in order to allow for email sending in the case of forgotten passwords.

##Configuration
In order to run the project, a Tomcat server must be setup. Right click the project and select properties, then go to servers and click on Tomcat. Next, MySQL needs to be configured properly. In the MVC Configuration file and spring-database.xml, set the proper port and name for the database ("dbase" for this project). This requires the username and password used in the MySQL workbench. The file titled dummyData.sql contains the scripts to create the tables as well as input some dummy data. Finally, set up Microsoft Outlook for an email address. The one used in this project was budgetapplicationproject@gmail.com. To use a different email, go to settings and enable POP and Microsoft Outlook should take care of the rest when given the email address and password. The email and password also need to be added to spring-mail.xml, along with the correct port (currently has the default). Also, modify the sending address in the sendEmail() method in the PasswordController.java file.

##Features
- Set a starting and ending point for a budget (multiple budgets cannot be active at the same time). 

- Past budgets can be viewed in the history page, which will display the entries added for the selected duration.

- Create, modify, and delete entries within a budgets. 

- Set limits for a budget. Setting a limit on a category will not allow a user to add an entry that causes the budget to overflow in this category. 

- View and search for other users, though only admins have the ability to modify other user's data.

- Admins have access to every user's budget entries, user data, and categories (which are listed when a user creates a budget entry).