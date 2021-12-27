package com.home.pio.controller;

import com.home.pio.entity.Node;
import com.home.pio.service.NodeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("v1/node")
public class NodeController {

    private NodeService nodeService;

    @GetMapping("")
    public Page<Node> index(Pageable pageable){
        return nodeService.search(pageable);
    }
}
