package com.dtinone.datashare.service;

import com.dtinone.datashare.entity.InformationContent;
import com.github.pagehelper.PageInfo;

public interface ContentService {

    PageInfo<InformationContent> list(InformationContent content, Integer pageNo, Integer pageSize, Integer menu);
}
