package com.lrchao.fakejob.network.request.business;

import com.lrchao.fakejob.model.json.home.HomeModel;
import com.lrchao.fakejob.network.request.PageRequest;
import com.lrchao.fakejob.network.request.RequestMethod;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2017/6/21 下午3:32
 */

public class HomeRequest extends PageRequest<HomeModel> {
    @Override
    public String getMethod() {
        return RequestMethod.GET;
    }

    @Override
    protected String getUrlPath() {
        return "fake/wall/v1.0/home-index.json";
    }
}
