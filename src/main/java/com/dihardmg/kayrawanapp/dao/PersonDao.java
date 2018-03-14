package com.dihardmg.kayrawanapp.dao;

import com.dihardmg.kayrawanapp.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Otorus
 * @since : 3/14/18
 */
@Repository
public interface PersonDao extends PagingAndSortingRepository<Person, String> {
    Page<Person> findByNamaContainingIgnoreCase(String nama, Pageable page);

}
