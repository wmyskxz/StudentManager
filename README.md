# StudentManager

基于 Servlet + JSP 实现的简易版的学生管理系统，原文地址：https://www.jianshu.com/p/553fc76bb8eb

---

## 在博客原项目中作了如下改进：

#### 改进一：Dao 层命名规范化

把之前的 StudentDAO 改为 StudentDao ，遵循 Java 类名使用 UpperCamelCase 风格的要求

#### 改进二：Dao 层改为接口并编写 Impl 实现类

```java
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
```

- 修改成接口后创建 StudentDaoImpl 实现类，然后将相应 Servlet 中稍作一下修改。

#### 改进三：增加删除提示

没有删除提示这个功能太蠢了，手滑一下就G....

首先我们在顶部的 <head> 标签中的 <script> 中增加一段代码

```javascript
function del() {
    var msg = "您真的确定要删除吗？\n\n请确认！";
    if (confirm(msg) == true) {
        return true;
    } else {
        return false;
    }
}
```

然后在删除 a 标签页中增加 onclick 属性：

```
onclick="javascript:return del();"
....就像下面这样....
td><a href="/deleteStudent?id=${s.id}" onclick="javascript:return del();"><span
        class="glyphicon glyphicon-trash"></span> </a></td>
```

#### 改进四：编辑页面自动勾选上性别

在当前的项目中，如果点击编辑按钮进入到编辑页面后，性别这个选项是空选的状态，这就很low：

![](https://github.com/wmyskxz/StudentManager/edit_noCheck.png)

这个也很简单，在 editStudent 页面增加一些判断就好了：

![](https://github.com/wmyskxz/StudentManager/edit_if.png)

用 `<c:if>` 标签来判断 sex 的值，然后根据对应的属性增加 checked 属性，这样就可以自动勾选上所对应的属性：

![](https://github.com/wmyskxz/StudentManager/edit_check.png)

#### 改进五：空值判断

我们允许设置为 null 的值仅仅为出生日期，其他的值均不允许出现空值，所以我们需要加入空值判断：

```
function checkEmpty(id, name) {
    var value = $("#" + id).val();
    if (value.length == 0) {
        alert(name + "不能为空");
        $("#" + id).focus();
        return false;
    }
    return true;
}
```

然后再为 form 创建一个 id 属性值为 “addForm” 并添加进判断空值的方法：

![](https://github.com/wmyskxz/StudentManager/checkEmpty1.png)

- **注意：** 这里需要写在 $(function(){}) 里面，等待文档加载完毕才能生效。
- 这里并没有为 sex 属性判断空值，我们采用一个简单的**为 sex 添加一个默认勾选项**来省略空值的判断。

同样的，我们也在编辑页面，采用同样的方法进行空值判断：

![](https://github.com/wmyskxz/StudentManager/checkEmpty2.png)

- 当进入编辑页面的时候已经有默认的勾选项了，所以 sex 值仍然不需要判空
