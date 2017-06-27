package com.wangzh.vultr.others.constants;

import android.graphics.Color;

/**
 * Created by 99210 on 2017/4/23.
 */

public class ConstValues {

    //callback中非httpexception的code处理
    public static final int ERROR_NONHTTP = -1;

    public static final String KEY_SUPPORTEDAPPVO = "SupportedAppVo";
    public static final String FILE_ROOT_DIRECTORY = "/VultrControlPanel";
    public static final String FILE_LOG = "/Log.txt";

    public static final int COLOR_SWIPEREFRESH_1 = Color.rgb(0, 199, 255);
    public static final int COLOR_SWIPEREFRESH_2 = Color.rgb(0,150,191);
    public static final int COLOR_SWIPEREFRESH_3 = Color.rgb(0,100,127);

    public static final String FRAGMENT_SUPPORTEDAPP = "fragment_supportedapp";
    public static final String FRAGMENT_MINEAPP = "fragment_mineapp";

    public static final int REQUEST_CODE_FROM_ACCOUNT = 0;
    public static final int RESULT_CODE_FROM_ACCOUNT = 1;

    public static final int REQUEST_NET_DELAY = 1000;

    public static final String BROWER_URL = "brower_url";

    public static final int OPERATE_STOP_SERVER_SUCCESS = 100;
    public static final int OPERATE_RESTART_SERVER_SUCCESS = 101;
}
