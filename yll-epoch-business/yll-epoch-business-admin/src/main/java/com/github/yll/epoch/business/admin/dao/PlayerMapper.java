package com.github.yll.epoch.business.admin.dao;

import com.github.yll.epoch.business.admin.model.Player;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
/**
 * @author luliang_yu
 * @date 2018/11/30
 */
@Mapper
@Component
public interface PlayerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Player record);

    int insertSelective(Player record);

    Player selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Player record);

    int updateByPrimaryKey(Player record);

    Player selectByUniqueKey(String id);

    @Select("SELECT * FROM player WHERE uk_id = #{id}")
    Player getOne(@Param("id") String id);

    @Select("SELECT * FROM player WHERE uk_id = #{id}")
    Player findByUkId(@Param("id") String id);

    @Insert("insert into player(uk_id,name,area,head,head_frame,star,sadou,create_timestamp,last_change_timestamp,bg) " +
            "values(#{ukId},#{name},#{area},#{head},#{headFrame},#{star},#{sadou},#{createTimestamp},#{lastChangeTimestamp},#{bg})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertPlayer(Player player);

    @Update("update player set token=#{token} where id=#{id}")
    void updateToken(@Param("id") Integer id, @Param("token") String token);

    @Select("SELECT * FROM player WHERE id = #{id} and token=#{token}")
    Player validToken(@Param("id") Integer id, @Param("token") String token);

    @Select("SELECT * FROM player WHERE id = #{id}")
    Player findById(@Param("id") Integer id);

    @Select("select IFNULL(sum(star),0) as totalStar from level_record where player_id=#{id}")
    Integer getTotalStar(@Param("id") Integer id);

    @Update("update player set bg=#{bgId} where id=#{id}")
    void changeBg(@Param("id") Integer id, @Param("bgId") Integer bgId);

    @Update("update player set sadou=sadou-#{num} where id=#{id}")
    void updateSadou(@Param("id") Integer id, @Param("num") Integer num);

    @Update("update player set sadou=sadou+#{num} where id=#{id}")
    void updateAddSadou(@Param("id") Integer id, @Param("num") Integer num);

    @Update("update player set area=#{area},head_frame=#{headFrame},name=#{name},last_change_timestamp=#{lastChangeTimestamp}" +
            " where id =#{id}")
    void update(Player p);
}