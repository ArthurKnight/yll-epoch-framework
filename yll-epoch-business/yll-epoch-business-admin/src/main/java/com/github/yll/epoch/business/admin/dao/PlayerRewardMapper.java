package com.github.yll.epoch.business.admin.dao;

import com.github.yll.epoch.business.admin.model.PlayerReward;
import com.github.yll.epoch.business.admin.model.Share;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author luliang_yu
 * @date 2018-11-23
 */

@Mapper
@Component
public interface PlayerRewardMapper {

    @Insert("insert into player_reward(player_id,last_reward_timestamp,times) " +
            "values(#{playerId},#{lastRewardTimestamp},#{times})")
    int insert(PlayerReward playerReward);

    @Select("select * from player_reward where player_id=#{id}")
    PlayerReward get(Integer id);

    /**
     * 获取奖励列表
     *
     * @return
     */
    @Select("select * from share order by delay_time asc")
    List<Share> getShareList();

    @Update("update player_reward set last_reward_timestamp = #{lastRewardTimestamp},times=#{times} where player_id = #{playerId}")
    int update(PlayerReward playerReward);

}
