package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ChooseFood;
import model.CookBook;
import model.FoodMaterial;

public class FoodDao {

    /**
     * Get the list of ingredients
     *
     * @return
     */
    public static List<String> getMaterials() {
        //get the list of the material from the database in MySQL
        String sql = "select * from food_material";

        List<String> foodMaterials = new ArrayList<String>();

        try {

            Statement stmt = DBManager.conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                foodMaterials.add(rs.getString("name"));
            }
        } catch (SQLException e) {
        }

        return foodMaterials;
    }


    /**
     * Get the menu information based on the condition
     *
     * @param condition
     * @return
     */
    public static List<CookBook> getCookBookByCondition(String condition) {
        /*get the menu information from the database "cookbook" based on conditions 
        e.g. materials (material wanted & material avoided), names
        */
        String sql = "select * from cook_book where 1=1 " + condition;

        List<CookBook> cookBooks = new ArrayList<>();

        try {
            Statement stmt = DBManager.conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                CookBook cookBook = new CookBook();
                cookBook.setId(rs.getInt("id"));
                cookBook.setName(rs.getString("name"));
                cookBook.setMaterial(rs.getString("material"));
                cookBook.setSteps(rs.getString("steps"));

                cookBooks.add(cookBook);
            }
        } catch (SQLException e) {
        }

        return cookBooks;
    }

    /**
     * Gets the menu record of the last few times
     *
     * @param last
     * @return
     */
    public static List<ChooseFood> getLastChooseFood(int last) {
        //get the menu record of the last few times from the database "choose food"
        String sql = "select cf.id,cf.book_id,cb.name,cb.material from choose_food cf,cook_book cb "
                + "where cf.book_id=cb.id order by cf.id desc limit  " + last;

        List<ChooseFood> cookBooks = new ArrayList<>();

        try {
            Statement stmt = DBManager.conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                ChooseFood cookBook = new ChooseFood();
                cookBook.setId(rs.getInt("book_id"));
                cookBook.setName(rs.getString("name"));
                cookBook.setMaterial(rs.getString("material"));

                cookBooks.add(cookBook);
            }
        } catch (SQLException e) {
        }

        return cookBooks;
    }

    /**
     * Insert selected dish records
     *
     * @param chooseFood
     */
    public static void insertChooseFood(ChooseFood chooseFood) {
        //insert the final selected menu into the database "choose food"
        String sql = "insert into choose_food values (null," + chooseFood.getBook_id() + ",sysdate())";

        try {
            Statement stmt = DBManager.conn.createStatement();

            stmt.executeUpdate(sql);

        } catch (SQLException e) {
        }
    }

    /**
     * insert　the information in the database cookbook
     *
     * @param cookBook
     */
    public static void insertCookBook(CookBook cookBook) {
        //insert　the information in the database cookbook
        String sql = "insert into cook_book values (null,'" + cookBook.getName() + "','" + cookBook.getMaterial() + "','" + cookBook.getSteps() + "')";

        try {
            Statement stmt = DBManager.conn.createStatement();

            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * get all the record from the database choosefood
     *
     * @return
     */
    public static List<ChooseFood> getAllChooseFood() {
        //get all the record from the database choosefood
        String sql = "select cf.id,cf.book_id,cb.name,cb.material,cf.createtime from choose_food cf,cook_book cb "
                + "where cf.book_id=cb.id order by cf.id desc";

        List<ChooseFood> cookBooks = new ArrayList<>();

        try {
            Statement stmt = DBManager.conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                ChooseFood cookBook = new ChooseFood();
                cookBook.setId(rs.getInt("book_id"));
                cookBook.setName(rs.getString("name"));
                cookBook.setMaterial(rs.getString("material"));
                cookBook.setTime(rs.getString("createtime"));
                cookBooks.add(cookBook);
            }
        } catch (SQLException e) {
        }

        return cookBooks;

    }

    /**
     * delete the information in the database cookbook
     *
     * @param id
     */
    public static void deleteCookBookByID(int id) {
        //delete the information in the database cookbook
        String sql = "delete from cook_book where id=" + id;

        try {
            Statement stmt = DBManager.conn.createStatement();

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
        }
       
    }




}
