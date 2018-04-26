package dao;

import bean.Student;

import java.util.List;

/**
 * Student 实体类的 Dao 类接口
 *
 * @author: @我没有三颗心脏
 * @create: 2018-04-26-上午 8:20
 */
public interface StudentDao {

	/**
	 * 获取数据总数目
	 * @return
	 */
	int getTotal();

	/**
	 * 增加一条数据
	 * @param student
	 */
	void add(Student student);

	/**
	 * 删除一条数据
	 * @param id
	 */
	void delete(int id);

	/**
	 * 更新一条数据
	 * @param student
	 */
	void update(Student student);

	/**
	 * 根据id返回一条数据
	 * @param id
	 * @return
	 */
	Student get(int id);

	/**
	 * list的简便方法，用于返回所有数据
	 * @return
	 */
	List<Student> list();

	/**
	 * 查询从start索引位置开始的count条数据
	 * @param start
	 * @param count
	 * @return
	 */
	List<Student> list(int start, int count);
}
