package bean;

import java.util.Date;

/**
 * 学生 POJO
 */
public class Student {

	private int id;				// 在数据库中的ID
	private int studentID;      // 学号，跟ID区分开为了方便数据库操作
	private String name;		// 姓名
	private int age;			// 年龄
	private String sex;			// 性别
	private Date birthday;		// 出生日期


	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int student_id) {
		this.studentID  = student_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
