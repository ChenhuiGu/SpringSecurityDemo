package cn.guchh.dao;

import cn.guchh.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author chenhuigu
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectMenuByUserId(Long id);
}
