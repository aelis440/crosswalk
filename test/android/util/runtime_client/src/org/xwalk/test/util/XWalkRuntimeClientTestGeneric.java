// Copyright (c) 2012 The Chromium Authors. All rights reserved.
// Copyright (c) 2013 Intel Corporation. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.xwalk.test.util;

import android.app.Activity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

import org.xwalk.app.runtime.XWalkRuntimeView;
import org.xwalk.app.XWalkRuntimeActivityBase;
import org.xwalk.test.util.XWalkRuntimeClientTestUtilBase.PageStatusCallback;

public class XWalkRuntimeClientTestGeneric<T extends XWalkRuntimeActivityBase>
        extends ActivityInstrumentationTestCase2<T> {
    private XWalkRuntimeView mRuntimeView;
    XWalkRuntimeClientTestUtilBase mTestUtil;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        final XWalkRuntimeActivityBase activity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                if (mRuntimeView == null) {
                    mRuntimeView = activity.getRuntimeView();
                }
                mTestUtil = new XWalkRuntimeClientTestUtilBase(mRuntimeView,
                        getInstrumentation());
                PageStatusCallback callback = mTestUtil.new PageStatusCallback();
                mRuntimeView.setCallbackForTest((Object)callback);
                postSetUp();
            }
        });
    }

    public void postSetUp() {
    }

    public XWalkRuntimeClientTestGeneric(Class<T> activityClass) {
        super(activityClass);
    }

    public XWalkRuntimeClientTestUtilBase getTestUtil() {
        return mTestUtil;
    }
}
