package model;

import java.sql.Date;

public class ChooseFood extends CookBook{
	
	private int id;
	private int book_id;
	private Date createtime;
        private String time;


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the book_id
	 */
	public int getBook_id() {
		return book_id;
	}

	/**
	 * @param book_id the book_id to set
	 */
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	/**
	 * @return the createtime
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
