package com.kongbig.task;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kongbig.model.Code;
import com.kongbig.model.RefreshTokenModel;
import com.kongbig.service.IAccessTokenService;
import com.kongbig.service.ICodeService;
import com.kongbig.service.IRefreshTokenService;
import com.kongbig.util.TimeUtils;

@Component
public class TimerTask {

	@Resource
	private ICodeService codeServiceImpl;
	@Resource
	private IRefreshTokenService refreshTokenServiceImpl;
	@Resource
	private IAccessTokenService accessTokenServiceImpl;

	/**
	 * code 30s清一次
	 *
	 */
	// @Scheduled(cron = "0/30 * * * * ?")
	public void deleteCode() {
		List<Code> codeList = codeServiceImpl.getAllCode();
		for (Code code : codeList) {
			// code过期，删除
			if (TimeUtils.isOutOfDate(code.getCreateTime(), code.getExpiresIn())) {
				codeServiceImpl.deleteCode(code.getCode());
			}
		}
	}

	/**
	 * refreshToken 30分钟清一次 
	 * accessToken跟着refreshToken一起清
	 */
	// @Scheduled(cron = "0 0/30 * * * ?")
	public void deleteCodeToken() {
		List<RefreshTokenModel> refreshTokenList = refreshTokenServiceImpl.getAllrefreshToken();
		for (RefreshTokenModel refreshToken : refreshTokenList) {
			// refreshToken过期，删除refreshToken和accessToken
			if (TimeUtils.isOutOfDate(refreshToken.getCreateTime(), refreshToken.getExpiresIn())) {
				accessTokenServiceImpl.deleteTokenByRtoken(refreshToken.getRefreshToken());
				refreshTokenServiceImpl.deleteRefreshToken(refreshToken.getRefreshToken());
			}
		}
	}
}
