package com.krekapps.indycarstats.models.data;

import com.krekapps.indycarstats.models.IntStat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by raefo on 25-Jun-17.
 */

@Repository
@Transactional
public interface IntStatDao extends CrudRepository<IntStat, Integer> {
}
