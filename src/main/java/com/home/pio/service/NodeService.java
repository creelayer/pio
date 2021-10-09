package com.home.pio.service;

import com.home.pio.entity.Node;
import com.home.pio.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class NodeService {

    @Value("${spring.config.name}")
    private String name;

    private final NodeRepository nodeRepository;

    public NodeService(NodeRepository nodeRepository){
        this.nodeRepository = nodeRepository;
    }

    public Node save(Node node) {
        return nodeRepository.save(node);
    }

    public Page<Node> search(Pageable pageable) {
        return nodeRepository.findAll(pageable);
    }

    public Node createDefaultNode() {
        Node node = nodeRepository.findByCurrentIsTrue();
        if (node == null) {
            node = new Node();
            node.current = true;
            node.name = this.name;
            nodeRepository.save(node);
        }
        return node;
    }

}
