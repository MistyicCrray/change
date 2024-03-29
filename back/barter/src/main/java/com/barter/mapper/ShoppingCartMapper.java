package com.barter.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.barter.model.ShoppingCart;

@Mapper
public interface ShoppingCartMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_shoppingcart
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    @Delete({
        "delete from t_shoppingcart",
        "where cartid = #{cartid,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String cartid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_shoppingcart
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    @Insert({
        "insert into t_shoppingcart (cartid, userid, ",
        "quantity, total, productid, ",
        "createdate, ischeck)",
        "values (#{cartid,jdbcType=VARCHAR}, #{userid,jdbcType=VARCHAR}, ",
        "#{quantity,jdbcType=INTEGER}, #{total,jdbcType=REAL}, #{productid,jdbcType=VARCHAR}, ",
        "#{createdate,jdbcType=TIMESTAMP}, #{ischeck,jdbcType=VARCHAR})"
    })
    int insert(ShoppingCart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_shoppingcart
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    @Select({
        "select",
        "cartid, userid, quantity, total, productid, createdate, ischeck",
        "from t_shoppingcart",
        "where cartid = #{cartid,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="cartid", property="cartid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="userid", property="userid", jdbcType=JdbcType.VARCHAR),
        @Result(column="quantity", property="quantity", jdbcType=JdbcType.INTEGER),
        @Result(column="total", property="total", jdbcType=JdbcType.REAL),
        @Result(column="productid", property="productid", jdbcType=JdbcType.VARCHAR),
        @Result(column="createdate", property="createdate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ischeck", property="ischeck", jdbcType=JdbcType.VARCHAR)
    })
    ShoppingCart selectByPrimaryKey(String cartid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_shoppingcart
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    @Select({
        "select",
        "cartid, userid, quantity, total, productid, createdate, ischeck",
        "from t_shoppingcart"
    })
    @Results({
        @Result(column="cartid", property="cartid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="userid", property="userid", jdbcType=JdbcType.VARCHAR),
        @Result(column="quantity", property="quantity", jdbcType=JdbcType.INTEGER),
        @Result(column="total", property="total", jdbcType=JdbcType.REAL),
        @Result(column="productid", property="productid", jdbcType=JdbcType.VARCHAR),
        @Result(column="createdate", property="createdate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ischeck", property="ischeck", jdbcType=JdbcType.VARCHAR)
    })
    List<ShoppingCart> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_shoppingcart
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    @Update({
        "update t_shoppingcart",
        "set userid = #{userid,jdbcType=VARCHAR},",
          "quantity = #{quantity,jdbcType=INTEGER},",
          "total = #{total,jdbcType=REAL},",
          "productid = #{productid,jdbcType=VARCHAR},",
          "createdate = #{createdate,jdbcType=TIMESTAMP},",
          "ischeck = #{ischeck,jdbcType=VARCHAR}",
        "where cartid = #{cartid,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(ShoppingCart record);
    

	// 连表查询
	@SelectProvider(type = com.barter.model.dyna.ShoppingCartDynaProvider.class, method = "selectProvider")
	@Results({ 
				@Result(column = "cartid", property = "cartid", jdbcType = JdbcType.VARCHAR, id = true),
				@Result(column = "userid", property = "userid", jdbcType = JdbcType.VARCHAR),
				@Result(column = "quantity", property = "quantity", jdbcType = JdbcType.INTEGER),
				@Result(column = "total", property = "total", jdbcType = JdbcType.REAL),
				@Result(column = "productid", property = "productid", jdbcType = JdbcType.VARCHAR),
				@Result(column = "createdate", property = "createdate", jdbcType = JdbcType.TIMESTAMP),
				@Result(column = "ischeck", property = "ischeck", jdbcType = JdbcType.VARCHAR),
				@Result(column = "proid", property = "proid", jdbcType = JdbcType.VARCHAR, id = true),
				@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
				@Result(column = "category", property = "category", jdbcType = JdbcType.VARCHAR),
				@Result(column = "price", property = "price", jdbcType = JdbcType.DOUBLE),
				@Result(column = "detail", property = "detail", jdbcType = JdbcType.VARCHAR),
				@Result(column = "createdate", property = "createdate", jdbcType = JdbcType.TIMESTAMP),
				@Result(column = "updatedate", property = "updatedate", jdbcType = JdbcType.TIMESTAMP),
				@Result(column = "createby", property = "createby", jdbcType = JdbcType.VARCHAR),
				@Result(column = "updateby", property = "updateby", jdbcType = JdbcType.VARCHAR),
				@Result(column = "color", property = "color", jdbcType = JdbcType.VARCHAR),
				@Result(column = "img", property = "img", jdbcType = JdbcType.VARCHAR),
				@Result(column = "userid", property = "userid", jdbcType = JdbcType.VARCHAR),
				@Result(column = "hits", property = "hits", jdbcType = JdbcType.INTEGER),
				@Result(column = "min_price", property = "minPrice", jdbcType = JdbcType.REAL),
				@Result(column = "faver", property = "faver", jdbcType = JdbcType.INTEGER),
				@Result(column = "status", property = "status", jdbcType = JdbcType.VARCHAR),
				@Result(column = "sellpoint", property = "sellpoint", jdbcType = JdbcType.VARCHAR),
				@Result(column = "isNotAuction", property = "isnotauction", jdbcType = JdbcType.VARCHAR),
				@Result(column = "max_price", property = "maxPrice", jdbcType = JdbcType.REAL),
				@Result(column = "quality", property = "quality", jdbcType = JdbcType.INTEGER) 
			})
	List<Map<String,Object>> findList(Map<String, Object> map);
	
	// 不连表查询
	@SelectProvider(type = com.barter.model.dyna.ShoppingCartDynaProvider.class, method = "selectBySql")
	@Results({
        @Result(column="cartid", property="cartid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="userid", property="userid", jdbcType=JdbcType.VARCHAR),
        @Result(column="quantity", property="quantity", jdbcType=JdbcType.INTEGER),
        @Result(column="total", property="total", jdbcType=JdbcType.REAL),
        @Result(column="productid", property="productid", jdbcType=JdbcType.VARCHAR),
        @Result(column="createdate", property="createdate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ischeck", property="ischeck", jdbcType=JdbcType.VARCHAR)
    })
	List<ShoppingCart> findBySql(Map<String, Object> map);

	@SelectProvider(type = com.barter.model.dyna.ShoppingCartDynaProvider.class, method = "updateProvider")
	Integer update(Map<String, Object> map);
}