package com.github.yll.epoch.business.admin.dao;

import com.github.yll.epoch.business.admin.model.LevelRecord;
import com.github.yll.epoch.business.admin.model.PlayerCard;
import com.github.yll.epoch.business.admin.vo.LevelRankVo;
import com.github.yll.epoch.business.admin.vo.LevelRecordInfoVo;
import com.github.yll.epoch.business.admin.vo.LevelRecordVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * @author luliang_yu
 * @date 2018/11/30
 */
@Mapper
@Component
public interface LevelRecordMapper {

    /**
     * 查询关卡
     *
     * @return
     */
    @Select("select * from level_record where player_id=#{playerId} and product_id=#{productId} and level_id = #{levelId}")
    LevelRecord findRecord(LevelRecord record);

    /**
     * 更新关卡
     *
     * @return
     */
    @Update("update level_record set star=#{star},record=#{record},cost_time=#{costTime},score=#{score}," +
            "create_timestamp=#{createTimestamp},last_change_timestamp=#{lastChangeTimestamp} " +
            "where player_id=#{playerId} and product_id=#{productId} and level_id = #{levelId}")
    void updateRecord(LevelRecord levelRecord);

    /**
     * 插入关卡记录
     *
     * @param record
     */
    @Insert("insert into level_record(player_id,level_id,product_id,star,record,cost_time,score,create_timestamp,last_change_timestamp)" +
            " values(#{playerId},#{levelId},#{productId},#{star},#{record},#{costTime},#{score},#{createTimestamp},#{lastChangeTimestamp})")
    void insert(LevelRecord record);

    /**
     * 获取玩家关卡列表
     *
     * @param playerId
     * @return
     */
    @Select("select product_id,IFNULL(sum(star),0)as stars from level_record where player_id=#{playerId} group by player_id,product_id")
    List<LevelRecordVo> getLevelRecordList(Integer playerId);

    /**
     * 获取关卡详情
     *
     * @param playerId
     * @param productId
     * @return
     */
    @Select("select level_id,star,record,cost_time,score from level_record where player_id=#{playerId} and product_id=#{productId}")
    List<LevelRecordInfoVo> getLevelRecord(@Param(value = "playerId") Integer playerId, @Param(value = "productId") Integer productId);

    /**
     * 排行榜-关卡-全服 排序规则 1.星星数star 2.积分score
     *
     * @param productId
     * @param levelId
     * @return
     */
    @Select("select player_id,p.name,p.head,p.head_frame,p.area,lr.star,score,cost_time,level_id from level_record lr left join player p on lr.player_id = p.id where product_id=#{productId} and level_id=#{levelId} order by star desc,score desc limit 0,100")
    List<LevelRankVo> rankingLevelList(@Param(value = "productId") Integer productId, @Param(value = "levelId") Integer levelId);

    /**
     * 排行榜-产品-全服 排序规则 1.星星数star 2.耗时costTime
     *
     * @param productId
     * @return
     */
    @Select("<script>select player_id,p.name,p.head,p.head_frame,p.area,sum(lr.star)star,sum(score)score,sum(cost_time)cost_time from level_record lr left join player p on lr.player_id = p.id where 1=1 " +
            "<if test='productId !=null'> and product_id=#{productId} </if>" +
            "group by player_id  order by star desc,cost_time desc limit 0,100 </script>")
    List<LevelRankVo> rankingProductList(@Param(value = "productId") Integer productId);

    /**
     * 根据作品id和玩家id获取玩家卡牌列表
     *
     * @param playerId
     * @param productId
     * @return
     */
    @Select("select pc.* from player_card pc where pc.player_id=#{playerId} and pc.product_id=#{productId} ")
    List<PlayerCard> achivementCardList(@Param(value = "playerId") Integer playerId, @Param(value = "productId") Integer productId);
}