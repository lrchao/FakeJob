package com.lrchao.fakejob.network.request;

import android.content.Intent;

import com.lrchao.fakejob.constant.BundleKey;
import com.lrchao.fakejob.exception.InitializationNotCompleteException;
import com.lrchao.fakejob.network.request.business.HomeRequest;
import com.lrchao.fakejob.network.request.business.JobCategoryListReq;
import com.lrchao.fakejob.network.request.business.JobDetailsReq;
import com.lrchao.fakejob.network.request.business.JobJoinReq;
import com.lrchao.fakejob.network.request.business.LoginReq;
import com.lrchao.fakejob.network.request.business.PartTimeJobReq;
import com.lrchao.fakejob.network.request.business.RegisterReq;
import com.lrchao.utils.LogUtils;


/**
 * Description:  发送请求的执行者
 *
 * @author lrc19860926@gmail.com
 * @date 2016/12/12 下午4:44
 */

public class RequestSender implements Runnable {


    private Intent mIntent;

    public RequestSender(Intent intent) {
        mIntent = intent;
    }

    @Override
    public void run() {

        BaseRequest mRequest = null;

        int requestAction = mIntent.getIntExtra(BundleKey.INTENT_EXTRA_REQUEST_ACTION, 0);

        LogUtils.d("RequestSender requestAction==" + requestAction);

        switch (requestAction) {


            case RequestActionKey.ACTION_GET_HOME:
                mRequest = new HomeRequest();
                break;

            case RequestActionKey.ACTION_GET_PART_TIME_JOB:
                mRequest = new PartTimeJobReq();
                break;

            case RequestActionKey.ACTION_GET_JOB_CATEGORY_LIST:
                mRequest = new JobCategoryListReq();
                break;

            case RequestActionKey.ACTION_GET_JOB_DETAILS:
                mRequest = new JobDetailsReq();
                break;

            case RequestActionKey.ACTION_GET_JOB_JOIN:
                mRequest = new JobJoinReq();
                break;

            case RequestActionKey.ACTION_LOGIN:
                mRequest = new LoginReq();
                break;

            case RequestActionKey.ACTION_REGISTER:
                mRequest = new RegisterReq();
                break;

//
//            case RequestActionKey.ACTION_GET_ASO_TASK_LIST:
//                mRequest = new ASOTaskListRequest();
//                break;
//
//            case RequestActionKey.ACTION_GET_ASO_TASK_DETAILS:
//                mRequest = new ASOTaskDetailsRequest();
//                break;
//
//            case RequestActionKey.ACTION_ASO_TASK_FINISH:
//                mRequest = new AsoTaskFinishRequest();
//                break;

            default:
                break;

        }
        if (mRequest == null) {
            throw new InitializationNotCompleteException("there is action: " + requestAction + " doesn't had request obj");
        }
        mRequest.setAction(requestAction);
        mRequest.setParamsIntent(mIntent);
        mRequest.execute();
    }
}
