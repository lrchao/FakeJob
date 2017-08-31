package com.lrchao.fakejob.network.request.business;


import com.lrchao.fakejob.model.json.job.PartTimeJobModel;
import com.lrchao.fakejob.network.request.PageRequest;
import com.lrchao.fakejob.network.request.RequestMethod;

import java.util.List;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/23 上午11:35
 */

public class PartTimeJobReq extends PageRequest<List<PartTimeJobModel>> {

    @Override
    public String getMethod() {
        return RequestMethod.GET;
    }

    @Override
    protected String getUrlPath() {
        return "fake/wall/v1.0/job-index.json";
    }
}
