package com.krekapps.indycarstats.models.data;

import com.krekapps.indycarstats.models.Track;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by ekk on 25-Jun-17.
 */

@Repository
@Transactional
public interface TrackDao extends CrudRepository<Track, Integer> {
}
