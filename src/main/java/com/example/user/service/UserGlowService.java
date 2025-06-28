package com.example.user.service;

import com.example.user.enums.GlowChangeType;
import com.example.user.model.UserGlow;
import com.example.user.model.UserGlowLog;
import com.example.user.repository.UserGlowLogRepository;
import com.example.user.repository.UserGlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDateTime;

@Service
public class UserGlowService {

    @Autowired
    private UserGlowRepository userGlowRepository;

    @Autowired
    private UserGlowLogRepository userGlowLogRepository;

    /**
     * 检查用户积分是否足够，并扣除指定积分
     * @param userId 用户ID
     * @param cost 要扣除的积分数
     * @throws IllegalStateException 如果积分不足
     */
    @Transactional
    public void checkAndDeductPoint(Long userId, int cost) {
        UserGlow glow = userGlowRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalStateException("用户积分信息不存在"));

        if (glow.getGlowPoint() < cost) {
            throw new IllegalStateException("积分不足，当前积分为：" + glow.getGlowPoint());
        }

        glow.setGlowPoint(glow.getGlowPoint() - cost);
        userGlowRepository.save(glow);

        // 写积分变更日志
        UserGlowLog log = new UserGlowLog();
        log.setUserId(userId);
        log.setChangeType(GlowChangeType.CONSUME);
        log.setChangeValue(-cost);
        log.setDescription("生成成就卡消耗积分");
        log.setCreatedAt(new Date(System.currentTimeMillis()));
        userGlowLogRepository.save(log);
    }

    public void addRecordPoint(Long userId) {
        updateGlow(userId, 1, GlowChangeType.RECORD, "记录日记");
    }

    public void addDialogPointIfThreshold(Long userId, int newDialogCount) {
        UserGlow glow = userGlowRepository.findByUserId(userId).orElseGet(() -> initGlow(userId));
        int oldDialog = glow.getTotalDialogues();
        int newTotalDialog = oldDialog + newDialogCount;
        int oldPoint = oldDialog / 5;
        int newPoint = newTotalDialog / 5;
        int gained = newPoint - oldPoint;

        if (gained > 0) {
            updateGlow(userId, gained, GlowChangeType.DIALOG, "对话达标：" + gained + " 点");
        }

        glow.setTotalDialogues(newTotalDialog);
        userGlowRepository.save(glow);
    }

    private void updateGlow(Long userId, int delta, GlowChangeType type, String desc) {
        UserGlow glow = userGlowRepository.findByUserId(userId).orElseGet(() -> initGlow(userId));
        glow.setGlowPoint(glow.getGlowPoint() + delta);
        glow.setTotalRecords(glow.getTotalRecords() + (type == GlowChangeType.RECORD ? 1 : 0));
        glow.setGlowLevel(calculateGlowLevel(glow.getGlowPoint()));
        userGlowRepository.save(glow);

        UserGlowLog log = new UserGlowLog();
        log.setUserId(userId);
        log.setChangeType(type);
        log.setChangeValue(delta);
        log.setCurrentTotal(glow.getGlowPoint());
        log.setDescription(desc);
        userGlowLogRepository.save(log);
    }

    private int calculateGlowLevel(int point) {
        int[] levels = {1, 5, 10, 20, 40};
        for (int i = levels.length - 1; i >= 0; i--) {
            if (point >= levels[i]) {
                return i + 1 + (point - levels[i]) / 20;
            }
        }
        return 0;
    }

    private UserGlow initGlow(Long userId) {
        UserGlow glow = new UserGlow();
        glow.setUserId(userId);
        glow.setGlowPoint(0);
        glow.setTotalDialogues(0);
        glow.setTotalRecords(0);
        glow.setGlowLevel(0);
        return glow;
    }

    public UserGlow getUserGlow(Long userId) {
        return userGlowRepository.findByUserId(userId)
                .orElseGet(() -> initGlow(userId));
    }
}
