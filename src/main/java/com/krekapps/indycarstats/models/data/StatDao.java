package com.krekapps.indycarstats.models.data;

import com.krekapps.indycarstats.models.DriverResult;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by ekk on 16-Jul-17.
 */
public interface StatDao extends MongoRepository<DriverResult, String> {

    Iterable<DriverResult> findByDriverid(int driverid);

    Iterable<DriverResult> findBySessionid(int sessionid);
}
