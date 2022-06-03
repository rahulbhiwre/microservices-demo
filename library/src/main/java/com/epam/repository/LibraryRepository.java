package com.epam.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.epam.entity.Library;

public interface LibraryRepository extends CrudRepository<Library, Long> {

	public Library findByUsernameAndBookid(String username,long bookid);

	public List<Library> findByUsername(String username);
}
