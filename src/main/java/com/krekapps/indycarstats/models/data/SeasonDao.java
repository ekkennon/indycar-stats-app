package com.krekapps.indycarstats.models.data;

import com.krekapps.indycarstats.models.Season;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by ekk on 25-Jun-17.
 */

@Repository
@Transactional
public interface SeasonDao extends CrudRepository<Season, Integer> {
}
