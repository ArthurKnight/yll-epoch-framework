package com.github.yll.epoch.business.admin.dao;

import com.github.yll.epoch.business.admin.model.Card;
import com.github.yll.epoch.business.admin.model.PlayerCard;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author luliang_yu
 * @date 2018-11-23
 */

@Mapper
@Component
public interface PlayerCardMapper {

    @Select("<script> select * from player_card <if test='id !=null'>where player_id =#{id} order by card_order asc</if></script>")
    List<PlayerCard> getCards(@Param("id") Integer id);

    @Update("<script> update player_card <set><if test='playerId !=null'>set player_id=#{playerId},</if> " +
            "<if test='cardId !=null'>set card_id=#{cardId},</if>" +
            "<if test='cardOrder !=null'>set card_order=#{cardOrder},</if>" +
            "</set> where player_id = #{player_id} and card_id=#{cardId} </script>")
    int updatePlayerCard(PlayerCard card);

    @Update("<script> update player_card set card_order=#{cardOrder} where player_id = #{id} and card_id=#{cardId}; " +
            "update player_card set card_order=#{changeCardOrder} where player_id = #{id} and card_id=#{changeCardId}</script>")
    void changeCardOrder(@Param("id") Integer id,
                         @Param("cardId") Integer cardId, @Param("cardOrder") Integer cardOrder,
                         @Param("changeCardId") Integer changeCardId, @Param("changeCardOrder") Integer changeCardOrder);

    @Select("select count(*) from player_card where player_id =#{id} and product_id=#{productId}")
    int countCards(@Param("id") Integer id, @Param("productId") Integer productId);

    @Select("select c.* from card c where c.work_id=#{productId} AND NOT EXISTS(" +
            "SELECT 1 from player_card pc where pc.player_id=#{id} and c.id=pc.card_id)")
    List<Card> getRandomCards(@Param("id") Integer id, @Param("productId") Integer productId);

    @Insert("insert into player_card(player_id,card_id,card_order,get_time,product_id,card_png) " +
            "values(#{playerId},#{cardId},#{cardOrder},#{getTime},#{productId},#{cardPng})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(PlayerCard playerCard);

    @Select("select max(card_order) from player_card where player_id=#{id}")
    int getMaxCardOrder(Integer id);
}
