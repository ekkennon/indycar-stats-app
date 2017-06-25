package com.krekapps.indycarstats.models.data;

import com.krekapps.indycarstats.models.Race;
import com.krekapps.indycarstats.models.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by ekk on 25-Jun-17.
 */

@Repository
@Transactional
public interface RaceDao extends CrudRepository<Race, Integer> {
}
