package cn.guchh.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MenuMapperTest {

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void select(){
        List<String> strings = menuMapper.selectMenuByUserId(2L);
        System.out.println(strings);
    }
}
