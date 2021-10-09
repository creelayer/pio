package com.home.pio.repository;

import com.home.pio.entity.Node;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NodeRepository extends PagingAndSortingRepository<Node, UUID> {
    Node findByCurrentIsTrue();
}
