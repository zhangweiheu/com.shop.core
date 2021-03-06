package com.shop.core.dao.impl;


import com.shop.core.dao.UserDao;
import com.shop.core.mapper.UserMapper;
import com.shop.core.model.User;
import com.shop.core.model.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangwei on 16/1/25.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> listAllUser(int offset, int size) {
        UserCondition condition = new UserCondition();
        condition.setLimitOffset(offset);
        condition.setLimitSize(size);
        return userMapper.selectByCondition(condition);
    }

    @Override
    public User findUserByName(String name) {
        UserCondition condition = new UserCondition();
        condition.createCriteria().andUsernameEqualTo(name);
        List<User> userList = userMapper.selectByCondition(condition);
        return null == userList || userList.size() == 0 ? null : userList.get(0);
    }

    @Override
    public User findById(int uid) {
        return userMapper.selectById(uid);
    }

    @Override
    public int deleteById(int uid) {
        User user = new User();
        user.setId(uid);
        user.setIsDelete(true);
        user.setUpdateAt(new Date());
        return userMapper.updateByIdSelective(user);
    }

    @Override
    public int saveUser(User user) {
        user.setCreateAt(new Date());
        user.setUpdateAt(new Date());
        return userMapper.insertSelective(user);
    }

    @Override
    public int updateUser(User user) {
        user.setUpdateAt(new Date());
        return userMapper.updateByIdSelective(user);
    }

    @Override
    public int CountByProperty(User user) {
        UserCondition condition = new UserCondition();
        UserCondition.Criteria criteria = condition.createCriteria();
        if (null != user && null != user.getIsAdmin()) {
            criteria.andIsAdminEqualTo(user.getIsAdmin());
        }
        if (null != user && null != user.getEmail()) {
            criteria.andEmailEqualTo(user.getEmail());
        }
        if (null != user && null != user.getUsername()) {
            criteria.andUsernameEqualTo(user.getUsername());
        }
        return userMapper.countByCondition(condition);
    }
}
