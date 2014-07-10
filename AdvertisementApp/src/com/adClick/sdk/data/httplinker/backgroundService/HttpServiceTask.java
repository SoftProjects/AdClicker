package com.adClick.sdk.data.httplinker.backgroundService;

import com.adClick.sdk.data.httplinker.HttpTask;

public class HttpServiceTask extends HttpTask{
	public HttpServiceTask(String baseurl, int method,long qid) {
		super(baseurl, method);
		this.qid=qid;
	}

	private long qid;

	public long getQid() {
		return qid;
	}
}
