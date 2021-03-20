package com.bsfit.suaf.impl;

import com.bsfit.suaf.mapper.ScoreMapper;
import com.bsfit.suaf.pojo.Score;
import com.bsfit.suaf.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/19 23:12
 * @verson
 */
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired(required = false)
    private ScoreMapper scoreMapper;

    @Override
    public List<Score> findAll(){
        return scoreMapper.findAll();
    }
}
