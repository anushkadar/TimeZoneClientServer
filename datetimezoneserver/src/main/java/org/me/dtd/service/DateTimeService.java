package org.me.dtd.service;

import org.me.dtd.entity.Data;
import org.me.dtd.repository.DataTimeJpaRepository;
import org.me.dtd.repository.DateTimeJdbcDao;
import org.me.dtd.repository.DateTimeJdbcTemplateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DateTimeService {

    @Autowired
    private DataTimeJpaRepository jpaRepo;
    @Autowired
    private DateTimeJdbcTemplateDao springDataDao;
    @Autowired
    private DateTimeJdbcDao jdbcDao;

    public List<Data> listAll() {
        return jpaRepo.findAll();
    }

    public Data saveJpa(Data data) {
        Data saved = jpaRepo.save(data);
        return jpaRepo.getById(saved.getId());
    }

    public Data saveJdbcTemplate(Data data) {
        Data saved = springDataDao.save(data);
        return springDataDao.get(saved.getId());
    }

    public Data saveJdbc(Data data) {
        Data saved = jdbcDao.save(data);
        return jdbcDao.get(saved.getId());
    }

    public Data get(Integer id) {
        return jpaRepo.findById(id).get();
    }

    public void delete(Integer id) {
        jpaRepo.deleteById(id);
    }
}
