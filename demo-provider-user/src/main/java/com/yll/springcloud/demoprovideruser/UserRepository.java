package com.yll.springcloud.demoprovideruser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Arthur
 * @date 2018-12-08 20:35
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
