package com.shopping.kshopping.craw.service;

import com.shopping.kshopping.craw.mapper.CrawMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Transactional
@Service("crawService")
public class CrawService {

    CrawMapper crawMapper;

}
